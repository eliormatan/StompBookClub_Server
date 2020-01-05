package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ErrorFrame extends BaseStompFrame{
    private String error;
    private int globalID;
    public ErrorFrame(String error,int globalID){
        super();
        this.error = error;
        this.globalID = globalID;
    }

    @Override
    public String makeItStomps() {
        String stompWise = "ERROR"+"\n"+
                "receipt-id:"+globalID+"\n"+
                "message:"+error+"\n"+
                "\u0000";
        return stompWise;
    }
}
