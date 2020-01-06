package bgu.spl.net.impl.CommandsAndStomps;

public class ReciptFrame extends BaseStompFrame{
    private int recipt;
    private String body;
    public ReciptFrame(int recipt,String body){
        super();
        this.recipt = recipt;
        this.body = body;
    }
    public String makeItStomps() {
        String stompWise = "RECEIPT"+"\n"+
                "receipt-id:"+recipt+"\n"+
                body+"\n"+
                "\u0000";
        return stompWise;
    }
}
