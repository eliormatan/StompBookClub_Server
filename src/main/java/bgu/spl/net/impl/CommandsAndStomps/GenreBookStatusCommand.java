package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class GenreBookStatusCommand implements StompFrames<StompBookClub> {
    String genre;

    public GenreBookStatusCommand(String genre) {
        this.genre = genre;
    }

    @Override
    public StompFrames run(StompBookClub arg) { //todo
        return arg.status(genre);
    }
}
