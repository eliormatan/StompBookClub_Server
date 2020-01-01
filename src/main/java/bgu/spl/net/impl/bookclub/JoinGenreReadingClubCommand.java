package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class JoinGenreReadingClubCommand implements Command<StompBookClub> {
    private String genre;
    public JoinGenreReadingClubCommand(String genre){
        this.genre = genre;
    }
    @Override
    public Serializable execute(StompBookClub arg) { //:todo
        arg.joinGenreReadingClub(genre);
        return "OK";
    }
}
