package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class ReturnBookCommand extends BaseStompFrame {
    private String genre;
    private String book;

    public ReturnBookCommand(String genre,String book) {
        super();
        this.genre = genre;
        this.book=book;
    }

    @Override
    public StompFrames run() {
        return null;
    }
}
