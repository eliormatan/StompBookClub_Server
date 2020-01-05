package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

public class ReciptFrame extends BaseStompFrame{
    private int recipt;
    public ReciptFrame(int recipt){
        super();
        this.recipt = recipt;
    }
    public String makeItStomps() {
        String stompWise = "RECEIPT"+"\n"+
                "receipt-id:"+recipt+"\n"+
                "\u0000";
        return stompWise;
    }
}
