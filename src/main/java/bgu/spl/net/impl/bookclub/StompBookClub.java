package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.CommandsAndStomps.StompFrames;
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
    public StompFrames login(String userName,String passWord){ //TODO: Return type and action
        return null;

    }
    public StompFrames logout(int recipt){
        return null;
    }
    public StompFrames joinGenreReadingClub(String genre){
        return null;

    }
    public StompFrames exitGenreReadingClub(int unsubscribeID){
        return null;

    }

    public StompFrames addBook(String genre, String book) {
        return null;
    }

    public StompFrames borrowBook(String genre, String book) {
        return null;
    }

    public StompFrames status(String genre) {
        return null;
    }

    public StompFrames returning(String genre, String book) {
        return null;
    }
}
