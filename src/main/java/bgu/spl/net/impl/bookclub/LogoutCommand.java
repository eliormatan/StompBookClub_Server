package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class LogoutCommand implements Command<StompBookClub> {
    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.logout();
        return "OK";
    }
}
