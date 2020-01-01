package bgu.spl.net.api;

import bgu.spl.net.srv.Connections;

public class StompMessagingProtocolImpl implements StompMessagingProtocol {
    @Override
    public void start(int connectionId, Connections<String> connections) {
        //TODO
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
