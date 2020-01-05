package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class GenreBookStatusCommand implements StompFrames<StompBookClub> {
    String genre;

    public GenreBookStatusCommand(String genre) {
        this.genre = genre;
    }

    @Override
    public void execute(StompBookClub arg) { //todo
        arg.status(genre);
    }
}
