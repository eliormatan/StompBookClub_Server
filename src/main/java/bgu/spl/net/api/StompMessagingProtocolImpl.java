package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.bookclub.User;
import bgu.spl.net.srv.Connections;

import java.nio.charset.StandardCharsets;

public class StompMessagingProtocolImpl<T> implements StompMessagingProtocol<T> {
    private int connectionId;
    private Connections connections;
    private boolean shouldTerminate;
    private User user;
    private StompBookClub stompBookClub;

    public StompMessagingProtocolImpl(StompBookClub stompBookClub) {
        this.stompBookClub = stompBookClub;
    }

    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.shouldTerminate = false;
        this.connectionId = connectionId;
        this.connections = connections;
        user = null;
    }

    @Override
    public void process(String message) { //TODO
        String result = message;
        String[] firstRowSeperator = result.split("\n", 2);
        if (firstRowSeperator[0].equals("CONNECT")) {
            OnConnect(firstRowSeperator[1]);
        }
        else if (firstRowSeperator[0].equals("DISCONNECT")) {
            OnDisconnect(firstRowSeperator[1]);
        }
        else if (firstRowSeperator[0].equals("SUBSCRIBE")) {
            OnSubscribe(firstRowSeperator[1]);
        }
        else if (firstRowSeperator[0].equals("UNSUBSCRIBE")) {
            OnUnsubscribe(firstRowSeperator[1]);
        }
        else if (firstRowSeperator[0].equals("SEND")) {
            OnSend(firstRowSeperator[1]);
        }

    }


    private void OnConnect(String restOfMsg) {
        String[] remainingRowSeperator = restOfMsg.split("\n", 5);
        String version = remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":") + 1);
        String userName = remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1);
        String passWord = remainingRowSeperator[3].substring(remainingRowSeperator[3].indexOf(":") + 1);
        int ans = stompBookClub.login(userName, passWord, connectionId);
        if (ans == 0) {
            //Complete Frame to the user
            connections.send(connectionId, new ConnectedFrame(version));
            user = stompBookClub.findUserByUniqueID(connectionId);
            user.setUniqueId(connectionId);

        } else if (ans == 1) {
            connections.send(connectionId, new ErrorFrame("User Already Logged IN", stompBookClub.getGlobalID()));
            shouldTerminate = true;
            connections.disconnect(connectionId);
            //Error Frame to the user - Already Logged IN
        } else {
            connections.send(connectionId, new ErrorFrame("User Has Wrong Password", stompBookClub.getGlobalID()));
            shouldTerminate = true;
            connections.disconnect(connectionId);
            //Error Frame to the user - Wrong PASS
        }

    }

    private void OnDisconnect(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 2);
            int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1));
            stompBookClub.logout(user);
            connections.send(connectionId, new ReciptFrame(reciptID, ""));
            connections.disconnect(connectionId);
            user = null;
            shouldTerminate = true;
        }
    }

    private void OnSubscribe(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 4);
            String genreToJoin = remainingRowSeperator[0];
            int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1));
            int subscribeID = Integer.parseInt(remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":") + 1));
            String bodyOfRecipt = stompBookClub.joinGenreReadingClub(user, genreToJoin, subscribeID);
            connections.send(connectionId, new ReciptFrame(reciptID, bodyOfRecipt));
        }
    }

    private void OnUnsubscribe(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 2);
            int unsubscribeID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1));
            String reciptBody = stompBookClub.exitGenreReadingClub(user, unsubscribeID);
            connections.send(connectionId, new ReciptFrame(unsubscribeID, reciptBody));
        }
    }

    private void OnSend(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 3);
            String destenation = remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1);
            int msgID = stompBookClub.getGlobalID();
            String userName = remainingRowSeperator[1].substring(0, remainingRowSeperator[1].indexOf(' '));
            int subscribeID = stompBookClub.findSubscribeID(destenation, userName);
            connections.send(destenation, new MessageFrame(subscribeID, msgID, destenation, remainingRowSeperator[1]));

        }
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
