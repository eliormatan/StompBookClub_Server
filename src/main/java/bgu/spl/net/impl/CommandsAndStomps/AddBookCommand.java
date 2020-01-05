package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class AddBookCommand extends BaseStompFrame {
    String genre;
    String book;
    public AddBookCommand(String genre,String book) {
        super();
        this.genre = genre;
        this.book=book;
    }
    @Override
    public StompFrames run() {
        return null;
    }
}
