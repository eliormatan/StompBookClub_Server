package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ExitGenreReadingClubCommand implements StompFrames<StompBookClub> {
    private String genre;
    public ExitGenreReadingClubCommand(String genre){
        this.genre = genre;
    }
    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.exitGenreReadingClub(genre);
        return "OK";
    }

}
