package bgu.spl.net.impl.bookclub;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Book {
    private String bookName;
    private String bookGenre;
    private ConcurrentLinkedDeque<User> borrowHistory;
    public Book(String bookName,String bookGenre){
        this.bookName = bookName;
        this.bookGenre=bookGenre;
        borrowHistory = new ConcurrentLinkedDeque<User>();
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public ConcurrentLinkedDeque<User> getBorrowHistory() {
        return borrowHistory;
    }

    public void addToBorrowHistory(User borrower) {
        this.borrowHistory.push(borrower);
    }
    public void removeFromBorrowHistory(){
        this.borrowHistory.pop();
    }

    public String getBookName() {
        return bookName;
    }
}
