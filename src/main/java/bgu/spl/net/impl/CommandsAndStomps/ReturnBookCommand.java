package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ReturnBookCommand implements StompFrames<StompBookClub> {
    String genre;
    String book;

    public ReturnBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }

    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.returning(genre,book);
        return null;
    }

}
