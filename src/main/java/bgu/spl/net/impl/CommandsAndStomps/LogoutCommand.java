package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;
import sun.rmi.runtime.Log;

import java.io.Serializable;

public class LogoutCommand implements StompFrames<StompBookClub> {
    private int recipt;
    public LogoutCommand(int recipt){
        this.recipt = recipt;
    }
    @Override
    public void execute(StompBookClub arg) { //todo
        arg.logout(recipt);
    }

}
