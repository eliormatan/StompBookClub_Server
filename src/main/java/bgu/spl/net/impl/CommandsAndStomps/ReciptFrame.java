package bgu.spl.net.impl.CommandsAndStomps;

public class ReciptFrame extends BaseStompFrame{
    private int recipt;
    private String body;
    public ReciptFrame(int recipt){
        super();
        this.recipt = recipt;
    }
    public String makeItStomps() {
        String stompWise = "RECEIPT"+"\n"+
                "receipt-id:"+recipt+"\n"+"\n"+
                "\u0000";
        return stompWise;
    }
}
