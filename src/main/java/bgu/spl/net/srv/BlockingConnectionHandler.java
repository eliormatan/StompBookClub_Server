package bgu.spl.net.srv;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.api.StompMsgEncoderDecoder;
import bgu.spl.net.impl.CommandsAndStomps.BaseStompFrame;
import bgu.spl.net.impl.CommandsAndStomps.StompFrames;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T> {

    private final StompMessagingProtocol<T> protocol;
    private final MessageEncoderDecoder encdec;
    private final Socket sock;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Connections connections;
    private int connectionID;
    private volatile boolean connected = true;

    public BlockingConnectionHandler(Socket sock, MessageEncoderDecoder reader, StompMessagingProtocol<T> protocol, Connections connections, int connectionID) {
        this.sock = sock;
        this.encdec = reader;
        this.protocol = protocol;
        this.connections = connections;
        this.connectionID = connectionID;
        protocol.setConnectionId(connectionID);
    }

    @Override
    public void run() {
        try (Socket sock = this.sock) { //just for automatic closing
            int read;
            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());
            protocol.start(connectionID,connections);
            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0) {
                String nextMessage = (String)encdec.decodeNextByte((byte) read);
                if (nextMessage != null) {
                    protocol.process(nextMessage);
                }
            }
            System.out.println("user is out");
            protocol.forceDisconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        protocol.forceDisconnect();
        connections.disconnect(connectionID);
        connected = false;
        sock.close();
    }

    @Override
    public void send(T msg) {
        if (msg != null) {
            try {
                out.write(encdec.encode(((StompFrames) msg).makeItStomps()));
                out.flush();
            } catch (IOException e) {
                protocol.forceDisconnect();
                connections.disconnect(connectionID);
                connected = false;
            }
        }
    }
}
