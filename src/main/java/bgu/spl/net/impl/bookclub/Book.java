package bgu.spl.net.impl.bookclub;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Book {
    private String bookName;
    private ConcurrentLinkedDeque<User> borrowHistory;
    public Book(String bookName){
        this.bookName = bookName;
        borrowHistory = new ConcurrentLinkedDeque<User>();
    }

}
