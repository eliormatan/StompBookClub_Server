package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.bookclub.User;

public class BaseStompFrame implements StompFrames {
    protected User actionCreator;
    protected StompBookClub bookClub = StompBookClub.getInstance();
    public StompFrames run() {
        return null;
    }
    public String makeItStomps(){return null;}
    public void setActionCreator(User user){this.actionCreator = user;}

}
