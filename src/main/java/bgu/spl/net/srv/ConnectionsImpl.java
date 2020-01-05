package bgu.spl.net.srv;

import java.nio.channels.Channel;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    private ConcurrentHashMap<Integer,ConnectionHandler<T>> connectionHashMap;
    private ConcurrentHashMap<Integer, List<String>> channelsHashMap;   //todo: where to implement the list?

    public ConnectionsImpl(){
        connectionHashMap= new ConcurrentHashMap<>();
        channelsHashMap = new ConcurrentHashMap<>();
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
        for(Integer curr: connectionHashMap.keySet()){
            List<String> currChannels=channelsHashMap.get(curr);
            if(currChannels!=null) {
                if(currChannels.contains(channel))
                    connectionHashMap.get(curr).send(msg);
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

    public void addToChannelsMap(int id, List<String> channels){
        channelsHashMap.put(id,channels);
    }

}