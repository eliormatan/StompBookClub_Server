package bgu.spl.net.impl.CommandsAndStomps;
import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class BorrowBookCommand implements StompFrames<StompBookClub> {
    String genre;
    String book;

    public BorrowBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }

    @Override
    public void execute(StompBookClub arg) {//todo
        arg.borrowBook(genre,book);
    }
}
