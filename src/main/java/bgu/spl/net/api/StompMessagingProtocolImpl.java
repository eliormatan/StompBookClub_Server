package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.srv.Connections;

import java.nio.charset.StandardCharsets;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    private int connectionId;
    private Connections<String> connections;
    private boolean shouldTerminate;

    public StompMessagingProtocolImpl(){
        this.shouldTerminate=false;
    }

    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(String message) { //TODO
        String result = message;
        String[] firstRowSeperator = result.split("\n", 2);
        switch(firstRowSeperator[0]){
            case "CONNECT":{
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",5);
                String userName = remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1);
                String passWord = remainingRowSeperator[3].substring(remainingRowSeperator[3].indexOf(":")+1);
                StompFrames loginCommand = new LogInCommand(userName,passWord);
            }
            case "DISCONNECT": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",2);
                int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1));
                LogoutCommand logoutCommand = new LogoutCommand(reciptID);
                shouldTerminate = true;
            }
            case "SUBSCRIBE": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",4);
                String whoToJoin = remainingRowSeperator[0];
                int subscribeID = Integer.parseInt(remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":")+1));
                int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1));
                JoinGenreReadingClubCommand subscribe = new JoinGenreReadingClubCommand(whoToJoin,subscribeID,reciptID);
            }
            case "UNSUBSCRIBE": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",2);
                int unsubscribeID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":")+1));
                ExitGenreReadingClubCommand unsubscribe = new ExitGenreReadingClubCommand(unsubscribeID);

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
