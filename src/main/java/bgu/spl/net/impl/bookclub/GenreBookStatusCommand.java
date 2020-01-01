package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class GenreBookStatusCommand implements Command<StompBookClub> {
    String genre;

    public GenreBookStatusCommand(String genre) {
        this.genre = genre;
    }

    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.status(genre);
        return null;
    }
}
