package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.rci.Command;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StompMsgEncoderDecoder implements MessageEncoderDecoder<StompFrames> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    @Override
    public StompFrames decodeNextByte(byte nextByte) {
        if (nextByte == '\u0000') {
            return msgParser();
        }
        pushByte(nextByte);
        return null; //not a full object yet
    }

    @Override
    public byte[] encode(StompFrames frame) {
        String encodedCommand = new String();
        return (encodedCommand + "\n" + "\u0000").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private StompFrames msgParser(){
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        String[] firstRowSeperator = result.split("\n", 2);
        switch(firstRowSeperator[0]){
            case "CONNECT":{
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",5);
                String userName = remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1);
                String passWord = remainingRowSeperator[3].substring(remainingRowSeperator[3].indexOf(":")+1);
                StompFrames loginCommand = new LogInCommand(userName,passWord);
                return loginCommand;
            }
            case "DISCONNECT": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",2);
                int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1));
                LogoutCommand logoutCommand = new LogoutCommand(reciptID);
                return logoutCommand;
            }
            case "SUBSCRIBE": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",4);
                String whoToJoin = remainingRowSeperator[0];
                int subscribeID = Integer.parseInt(remainingRowSeperator[1].substring(remainingRowSeperator[1].indexOf(":")+1));
                int reciptID = Integer.parseInt(remainingRowSeperator[2].substring(remainingRowSeperator[2].indexOf(":")+1));
                JoinGenreReadingClubCommand subscribe = new JoinGenreReadingClubCommand(whoToJoin,subscribeID,reciptID);
                return subscribe;
            }
            case "UNSUBSCRIBE": {
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",2);
                int unsubscribeID = Integer.parseInt(remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":")+1));
                ExitGenreReadingClubCommand unsubscribe = new ExitGenreReadingClubCommand(unsubscribeID);
                return unsubscribe;

            }
            case "SEND":{
                String[] remainingRowSeperator = firstRowSeperator[1].split("\n",3);
                String destenation = remainingRowSeperator[0].substring(remainingRowSeperator[0].indexOf(":")+1);
                //TODO
            }

        }
        return null; //TODO

    }

}
