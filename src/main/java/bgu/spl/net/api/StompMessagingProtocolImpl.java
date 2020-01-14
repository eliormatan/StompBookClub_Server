package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.bookclub.User;
import bgu.spl.net.srv.Connections;
import javafx.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StompMessagingProtocolImpl<T> implements StompMessagingProtocol<T> {
    private int connectionId;
    private Connections connections;
    private boolean shouldTerminate;
    private User user;
    private StompBookClub stompBookClub;

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

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
    public void process(String message) {
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
        if(user==null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 5);
            String version = remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":") + 1);
            String userName = remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1);
            String passWord = remainingRowSeperator[3].substring(remainingRowSeperator[3].indexOf(":") + 1);
            int ans = stompBookClub.login(userName, passWord, connectionId);
            if (ans == 0) {
                //Complete Frame to the user
                if (!connections.send(connectionId, new ConnectedFrame(version)))
                    forceDisconnect();
                user = stompBookClub.findUserByUniqueID(connectionId);
                user.setUniqueId(connectionId);
            } else if (ans == 1) {
                if (!connections.send(connectionId, new ErrorFrame("User Already Logged IN", stompBookClub.getGlobalID())))
                    forceDisconnect();
                shouldTerminate = true;
                connections.disconnect(connectionId);
                //Error Frame to the user - Already Logged IN
            } else {
                if (!connections.send(connectionId, new ErrorFrame("User Has Wrong Password", stompBookClub.getGlobalID())))
                    forceDisconnect();
                shouldTerminate = true;
                connections.disconnect(connectionId);
                //Error Frame to the user - Wrong PASS
            }
        }
    }

    private void OnDisconnect(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 2);
            int reciptID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1));
            stompBookClub.logout(user);
            connections.send(connectionId, new ReciptFrame(reciptID));
            user = null;
            shouldTerminate = true;
            connections.disconnect(connectionId);
        }
    }

    private void OnSubscribe(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 4);
            String genreToJoin = remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1);
            int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1));
            int subscribeID = Integer.parseInt(remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":") + 1));
            stompBookClub.joinGenreReadingClub(user, genreToJoin, subscribeID);
            if(!connections.send(connectionId, new ReciptFrame(reciptID)))
                forceDisconnect();
        }
    }

    private void OnUnsubscribe(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 2);
            int unsubscribeID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1));
            stompBookClub.exitGenreReadingClub(user, unsubscribeID);
            if(!connections.send(connectionId, new ReciptFrame(unsubscribeID)))
                forceDisconnect();
        }
    }

    private void OnSend(String restOfMsg) {
        if (user != null) {
            String[] remainingRowSeperator = restOfMsg.split("\n", 4);
            String destenation = remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1);
            int msgID = stompBookClub.getGlobalID();
            String userName;
            if(remainingRowSeperator[2].indexOf(' ')!=-1) {
                 userName = remainingRowSeperator[2].substring(0, remainingRowSeperator[2].indexOf(' '));
            }
            else
            {
                userName = remainingRowSeperator[2].substring(0, remainingRowSeperator[2].indexOf(':'));
            }
            CopyOnWriteArrayList<Pair<User,Integer>> genreArray = stompBookClub.getRegisterdToGenreMap().get(destenation);
            if(genreArray!=null) {
                synchronized (genreArray) {
                    for (Pair<User, Integer> user : genreArray) {
                        if (connections.send(user.getKey().getUniqueId(), new MessageFrame(user.getValue(), msgID, destenation, remainingRowSeperator[2])))
                            forceDisconnect();
                    }
                }
            }
        }
    }


    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    public void forceDisconnect(){
        if(user!=null) {
            user.setLogin(false);
        }
    }
}
