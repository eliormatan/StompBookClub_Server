package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.CommandsAndStomps.StompFrames;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StompBookClub {
    private static StompBookClub bookClubInstance = new StompBookClub();
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Pair<User,Integer>>> registerdToGenreMap;
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
    public int findSubscribeID(String genre,String userName){
        CopyOnWriteArrayList<Pair<User,Integer>> subscribedList = registerdToGenreMap.get(genre);
        if(subscribedList!=null){
            for (Pair<User, Integer> pair : subscribedList) {
                if (pair.getKey().getUsername().equals(userName)) {
                    return pair.getValue();
                }
            }
        }
        return -1;
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
    public int login(String userName, String passWord, int connectionID){
        synchronized (this) {
            if (listOfUsers.containsKey(userName)) {
                User userInSystem = listOfUsers.get(userName);
                if (!userInSystem.isLogin()) {
                    if (userInSystem.getPassword().equals(passWord)) {
                        userInSystem.setLogin(true);
                        userInSystem.setUniqueId(connectionID);
                        return 0; // 0 Represents All OK
                    } else {
                        return 2; //2 Resresents User Who gave incorrect Pass
                    }
                } else {
                    return 1; //1 Represents User Who already Logged IN
                }
            } else {
                User newUser = new User(userName, passWord);
                newUser.setUniqueId(connectionID);
                newUser.setLogin(true);
                listOfUsers.put(userName, newUser);
                return 0; // 0 Represents All OK
            }
        }

    }
    public void logout(User user){
        user.setLogin(false);
        user.setUniqueId(-1);

    }
    public void joinGenreReadingClub(User user,String genre,int subscriptionID){
        if(!registerdToGenreMap.containsKey(genre)){
            registerdToGenreMap.put(genre,new CopyOnWriteArrayList<>());
        }
        CopyOnWriteArrayList<Pair<User,Integer>> genreArr = registerdToGenreMap.get(genre);
        for(Pair<User,Integer> p:genreArr){
            if(p.getKey() == user){
                genreArr.remove(p);
                break;
            }
        }
        registerdToGenreMap.get(genre).add(new Pair(user,subscriptionID));
    }
    public void exitGenreReadingClub(User user,int unsubscribeID){
        String genreAns;
        for (Map.Entry<String, CopyOnWriteArrayList<Pair<User, Integer>>> entry : registerdToGenreMap.entrySet()){
            genreAns = entry.getKey();
            for(Pair<User,Integer> pair: entry.getValue()){
                if(pair.getValue().equals(unsubscribeID) & pair.getKey().equals(user))
                {
                    CopyOnWriteArrayList<Pair<User,Integer>> genreArray = registerdToGenreMap.get(genreAns);
                    synchronized (genreArray) {
                        registerdToGenreMap.get(genreAns).remove(pair);
                    }
                    break;
                }
            }
        }
    }

    public ConcurrentHashMap<String, CopyOnWriteArrayList<Pair<User, Integer>>> getRegisterdToGenreMap() {
        return registerdToGenreMap;
    }

}
