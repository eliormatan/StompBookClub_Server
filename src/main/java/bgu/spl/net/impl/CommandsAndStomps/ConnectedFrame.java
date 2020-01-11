package bgu.spl.net.impl.CommandsAndStomps;

public class ConnectedFrame extends BaseStompFrame{
    private String version;
    public ConnectedFrame(String version){
        super();
        this.version = version;
    }

    @Override
    public String makeItStomps() {
        String stompWise = "CONNECTED"+"\n"
                +"version:"+version+"\n"+"\n"+
                "\u0000";
        return stompWise;
    }

}
