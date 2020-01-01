package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class LogoutCommand implements StompFrames<StompBookClub> {
    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.logout();
        return "OK";
    }

}
