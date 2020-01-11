package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

public class MessageFrame extends BaseStompFrame {
    private int subscriptionID;
    private int msgID;
    private String destenation;
    private String body;

    public MessageFrame(int subscriptionID, int msgID, String destenation,String body) {
        this.subscriptionID = subscriptionID;
        this.msgID = msgID;
        this.destenation = destenation;
        this.body = body;
    }

    public String makeItStomps(){
        String stompWise = "MESSAGE"+"\n"+
                "subscription:"+subscriptionID+"\n"+
                "Message-id:"+msgID+"\n"+
                "destination:"+destenation+"\n"+"\n"+
                body+
                "\u0000";
        return stompWise;
    }

}
