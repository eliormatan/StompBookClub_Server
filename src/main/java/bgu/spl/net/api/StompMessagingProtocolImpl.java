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
//        String[] result = message.split(System.lineSeparator(), 2);
//        String actionTodo = result[0];
////        switch(actionTodo){
////            case "SEND":
////            {
////
////            }
////            case "SUBSCRIBE":
////            {
////
////            }
////            case "UNSUBSCRIBE":
////            {
////
////            }
////            case "DISCONNECT":
////            {
////
////            }
////            case "CONNECT":
////            {
////
////            }
////            case "MESSAGE":
////            {
////
////            }
//        }
    }

    @Override
    public boolean shouldTerminate() { //TODO
        return false;
    } //TODO
}
