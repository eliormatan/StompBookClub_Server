package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class JoinGenreReadingClubCommand extends BaseStompFrame {
    private String genre;
    private int reciptID;
    private int subscribeID;
    public JoinGenreReadingClubCommand(String genre,int subscribeID,int reciptID){
        this.genre = genre;
        this.reciptID = reciptID;
        this.subscribeID = subscribeID;
    }
    @Override
    public StompFrames run() {
        return null;
    }
}
