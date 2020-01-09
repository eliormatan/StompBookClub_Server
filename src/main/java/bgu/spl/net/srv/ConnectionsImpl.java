package bgu.spl.net.srv;

import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.bookclub.User;
import javafx.util.Pair;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionsImpl<T> implements Connections<T> {

    private ConcurrentHashMap<Integer,ConnectionHandler<T>> connectionHashMap;

    public ConnectionsImpl(){
        connectionHashMap= new ConcurrentHashMap<>();
    }

    @Override
    public boolean send(int connectionId, T msg) {
        ConnectionHandler connectionIdHandler = connectionHashMap.get(connectionId);
        if(connectionIdHandler!=null){
            connectionIdHandler.send(msg);
            return true;
        }
        return false;
    }

    @Override
    public void send(String channel, T msg) {
        CopyOnWriteArrayList<Pair<User,Integer>> usersOfGenre= StompBookClub.getInstance().getRegisterdToGenreMap().get(channel);
        if(usersOfGenre!=null) {
            for (Pair<User, Integer> currUser : usersOfGenre) {
                ConnectionHandler connectionIdHandler = connectionHashMap.get(currUser.getKey().getUniqueId());
                if (connectionIdHandler != null)
                    connectionIdHandler.send(msg);
            }
        }
    }

    @Override
    public void disconnect(int connectionId) {
        connectionHashMap.remove(connectionId);
    }

    public void addToConnectionMap(int id, ConnectionHandler connectionHandler) {
        connectionHashMap.put(id, connectionHandler);
    }

}