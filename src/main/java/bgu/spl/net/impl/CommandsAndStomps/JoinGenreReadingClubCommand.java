package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class JoinGenreReadingClubCommand implements StompFrames<StompBookClub> {
    private String genre;
    private int reciptID;
    private int subscribeID;
    public JoinGenreReadingClubCommand(String genre,int subscribeID,int reciptID){
        this.genre = genre;
        this.reciptID = reciptID;
        this.subscribeID = subscribeID;
    }
    @Override
    public void execute(StompBookClub arg) { //:todo
        arg.joinGenreReadingClub(genre);
    }


}
