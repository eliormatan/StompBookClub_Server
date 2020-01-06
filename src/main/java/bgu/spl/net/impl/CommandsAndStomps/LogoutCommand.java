package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;
import sun.rmi.runtime.Log;

import java.io.Serializable;

public class LogoutCommand extends BaseStompFrame {
    private int recipt;
    public LogoutCommand(int recipt){
        this.recipt = recipt;
    }
//    @Override
//    public StompFrames run(StompBookClub arg) { //todo
//        return arg.logout(recipt);
//    }

    @Override
    public StompFrames run() {
        actionCreator.setLogin(false);
        actionCreator.setUniqueId(-1);
        return new ReciptFrame(recipt);
    }
}
