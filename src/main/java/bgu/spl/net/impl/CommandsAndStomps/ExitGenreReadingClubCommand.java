package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ExitGenreReadingClubCommand extends BaseStompFrame {
    private int unsubscribeID;
    public ExitGenreReadingClubCommand(int unsubscribeID){
        this.unsubscribeID = unsubscribeID;
    }

    @Override
    public StompFrames run() {
        return null;
    }
}
