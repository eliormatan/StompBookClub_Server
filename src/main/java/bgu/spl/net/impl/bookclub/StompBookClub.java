package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.RCIClient;
import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class StompBookClub {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<Pair<String, CopyOnWriteArrayList<String>>>> registerdToGenreMap;
    ConcurrentHashMap<String,Pair<String,Boolean>> listOfUsers;
    public void login(String userName,String passWord){ //TODO: Return type and action

    }

    public void status(String genre) {//todo: return All the subscribed users and their inventory
    }

    public void returning(String genre, String book) { //todo: return book name+book lender
    }

    public void borrow(String genre, String book) { //todo: implement
    }

    public void add(String genre, String book) {    //todo: add the book to the client inventory.
    }

}
