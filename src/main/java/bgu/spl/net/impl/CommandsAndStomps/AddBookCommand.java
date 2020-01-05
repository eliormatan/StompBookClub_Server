package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class AddBookCommand implements StompFrames {
    String genre;
    String book;
    public AddBookCommand(String genre,String book) {
        this.genre = genre;
        this.book=book;
    }
    @Override
    public StompFrames run() {
        return null;
    }
}
