package bgu.spl.net.api;

import bgu.spl.net.srv.Connections;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    private int connectionId;
    private Connections<String> connections;
    private boolean shouldTerminate;

    public StompMessagingProtocolImpl(){
        this.shouldTerminate=false;
    }

    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(String message) { //TODO

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
