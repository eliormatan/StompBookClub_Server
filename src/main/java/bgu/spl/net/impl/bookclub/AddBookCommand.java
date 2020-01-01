package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class AddBookCommand implements Command<StompBookClub> {
    String genre;
    String book;
    public AddBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }
    @Override
    public Serializable execute(StompBookClub arg) { //todo
        arg.add(genre,book);
        return null;
    }
}
