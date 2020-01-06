package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.bookclub.User;
import bgu.spl.net.srv.Connections;

import java.nio.charset.StandardCharsets;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    private int connectionId;
    private Connections connections;
    private boolean shouldTerminate;
    private User user;
    private StompBookClub stompBookClub;

    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.shouldTerminate = false;
        this.connectionId = connectionId;
        this.connections = connections;
        user = null;
        stompBookClub = StompBookClub.getInstance();
    }

    @Override
    public void process(String message) { //TODO
        String result = message;
        String[] firstRowSeperator = result.split("\n", 2);
        switch(firstRowSeperator[0]){
            case "CONNECT":{
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",5);
                String version = remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":")+1);;
                String userName = remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1);
                String passWord = remainingRowSeperator[3].substring(remainingRowSeperator[3].indexOf(":")+1);
                StompFrames loginCommand = new LogInCommand(userName,passWord,connectionId,version);
                StompFrames response = loginCommand.run();
                connections.send(connectionId,response);
                if(response instanceof ErrorFrame)
                {
                    shouldTerminate = true;
                    connections.disconnect(connectionId);
                }
                else{
                    user = stompBookClub.findUserByUniqueID(connectionId);
                }
            }
            case "DISCONNECT": {
                if(user!=null) {
                    String[] remainingRowSeperator = firstRowSeperator[1].split("\n", 2);
                    int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1));
                    LogoutCommand logoutCommand = new LogoutCommand(reciptID);
                    logoutCommand.setActionCreator(user);
                    StompFrames frame = logoutCommand.run();
                    connections.send(connectionId, frame);
                    connections.disconnect(connectionId);
                    user=null;
                    shouldTerminate = true;
                }
            }
            case "SUBSCRIBE": { //NOT LOGGED IN
                if(user!=null) {
                    String[] remainingRowSeperator = firstRowSeperator[1].split("\n", 4);
                    String whoToJoin = remainingRowSeperator[0];
                    int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":") + 1));
                    int subscribeID = Integer.parseInt(remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":") + 1));
                    JoinGenreReadingClubCommand subscribe = new JoinGenreReadingClubCommand(whoToJoin, subscribeID, reciptID);
                    subscribe.setActionCreator(user);
                    StompFrames response = subscribe.run();
                    connections.send(connectionId,response);
                }

            }
            case "UNSUBSCRIBE": {
                if(user!=null) {
                    String[] remainingRowSeperator = firstRowSeperator[1].split("\n", 2);
                    int unsubscribeID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":") + 1));
                    ExitGenreReadingClubCommand unsubscribe = new ExitGenreReadingClubCommand(unsubscribeID);
                    unsubscribe.setActionCreator(user);
                    unsubscribe.run();
                }

            }
            case "SEND":{
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",3);
                String destenation = remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":")+1);
                //TODO
            }

        }

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
