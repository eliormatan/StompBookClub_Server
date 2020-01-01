package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class ExitGenreReadingClubCommand implements Command<StompBookClub> {
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
