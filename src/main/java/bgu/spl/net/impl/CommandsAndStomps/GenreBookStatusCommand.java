package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class GenreBookStatusCommand extends BaseStompFrame {
    String genre;

    public GenreBookStatusCommand(String genre) {
        this.genre = genre;
    }

    @Override
    public StompFrames run() {
        return null;
    }
}
