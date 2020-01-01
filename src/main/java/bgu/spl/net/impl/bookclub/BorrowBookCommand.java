package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class BorrowBookCommand implements Command<StompBookClub> {
    String genre;
    String book;

    public BorrowBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }

    @Override
    public Serializable execute(StompBookClub arg) {//todo
        arg.borrow(genre,book);
        return null;
    }
}
