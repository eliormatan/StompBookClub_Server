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
    public void logout(){

    }
    public void joinGenreReadingClub(String genre){

    }
    public void exitGenreReadingClub(String genre){

    }

    public void addBook(String genre, String book) {
    }

    public void borrowBook(String genre, String book) {

    }

    public void status(String genre) {
    }

    public void returning(String genre, String book) {
    }
}
