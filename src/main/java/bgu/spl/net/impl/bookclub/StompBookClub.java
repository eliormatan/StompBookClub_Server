package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.RCIClient;
import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class StompBookClub {
    private static StompBookClub bookClubInstance = new StompBookClub();
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<Pair<String, CopyOnWriteArrayList<String>>>> registerdToGenreMap;
    private ConcurrentHashMap<String,Pair<String,Boolean>> listOfUsers;
    private StompBookClub(){
        registerdToGenreMap = new ConcurrentHashMap<>();
        listOfUsers = new ConcurrentHashMap<>();
    }
    public static StompBookClub getInstance(){ return bookClubInstance; }
    public void login(String userName,String passWord){ //TODO: Return type and action

    }
    public void logout(int recipt){

    }
    public void joinGenreReadingClub(String genre){

    }
    public void exitGenreReadingClub(int unsubscribeID){

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
