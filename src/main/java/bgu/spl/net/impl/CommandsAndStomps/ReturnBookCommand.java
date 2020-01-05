package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ReturnBookCommand extends BaseStompFrame {

    String genre;
    String book;

    public ReturnBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }

//    @Override
//    public StompFrames run(StompBookClub arg) { //todo
//        arg.returning(genre,book);
//        return null;
//    }

    @Override
    public StompFrames run() {
        return null;
    }
}
