package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    ConcurrentHashMap<Integer,ConnectionHandler<T>> connectionHashMap = new ConcurrentHashMap<>();
    @Override

    public boolean send(int connectionId, T msg) {
        return false; //TODO
    }

    @Override
    public void send(String channel, T msg) {
        //TODO
    }

    @Override
    public void disconnect(int connectionId) {
        //TODO
    }
}
