package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.api.StompMsgEncoderDecoder;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.srv.Server;

public class StompServer {

    public static void main(String[] args) { //todo
        StompBookClub bookClub = StompBookClub.getInstance(); //one shared object
// you can use any server...
        if (args[1] == "tpc") {
            Server.threadPerClient(
                    Integer.parseInt(args[0]), //port
                    () -> new StompMessagingProtocolImpl<>(bookClub), //protocol factory
                    StompMsgEncoderDecoder::new //message encoder decoder factory
            ).serve();
        }
        else {
            Server.reactor(
                    Runtime.getRuntime().availableProcessors(),
                    Integer.parseInt(args[0]), //port
                    () -> new StompMessagingProtocolImpl<>(bookClub), //protocol factory
                    StompMsgEncoderDecoder::new //message encoder decoder factory
            ).serve();
        }
    }


}
