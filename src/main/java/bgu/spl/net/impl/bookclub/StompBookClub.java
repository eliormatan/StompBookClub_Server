package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.CommandsAndStomps.StompFrames;
import bgu.spl.net.impl.rci.RCIClient;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class StompBookClub {
    private static StompBookClub bookClubInstance = new StompBookClub();
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<User>> registerdToGenreMap;
    private ConcurrentHashMap<String,User> listOfUsers;
    private int globalID;
    private StompBookClub(){
        registerdToGenreMap = new ConcurrentHashMap<>();
        listOfUsers = new ConcurrentHashMap<>();
        globalID = 0;
    }
    public static StompBookClub getInstance(){ return bookClubInstance; }

    public int getGlobalID() {
        globalID++;
        return globalID;
    }
    public User findUserByUniqueID(int uniqueID){
        Iterator<User> valueIterator=listOfUsers.values().iterator();
        while(valueIterator.hasNext()) {
            User next = valueIterator.next();
            if(next.getUniqueId() == uniqueID)
                return next;
        }
        return null;
    }
    public int login(String userName, String passWord, int connectionID){ //TODO: Return type and action
        if(listOfUsers.contains(userName)){
            User userInSystem = listOfUsers.get(userName);
            if(!userInSystem.isLogin()) {
                if (userInSystem.getPassword().equals(passWord)) {
                    userInSystem.setLogin(true);
                    userInSystem.setUniqueId(connectionID);
                    return 0; // 0 Represents All OK
                }
                else {
                    return 2; //2 Resresents User Who gave incorrect Pass
                }
            }
            else
            {
                return 1; //1 Represents User Who already Logged IN
            }
        }
        else{
            User newUser = new User(userName,passWord);
            newUser.setUniqueId(connectionID);
            newUser.setLogin(true);
            listOfUsers.put(userName,newUser);
            return 0; // 0 Represents All OK
        }

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
