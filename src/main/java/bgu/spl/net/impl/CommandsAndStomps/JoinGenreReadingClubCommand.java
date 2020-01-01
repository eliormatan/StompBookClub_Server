package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class JoinGenreReadingClubCommand implements StompFrames<StompBookClub> {
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
