package bgu.spl.net.api;

import bgu.spl.net.srv.Connections;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    int connectionId;
    Connections<String> connections;
    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(String message) {
        //TODO
    }

    @Override
    public boolean shouldTerminate() { //TODO
        return false;
    }
}
