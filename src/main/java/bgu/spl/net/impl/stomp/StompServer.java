package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.StompMessagingProtocolImpl;
import bgu.spl.net.api.StompMsgEncoderDecoder;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.srv.Server;

public class StompServer {

    public static void main(String[] args) { //todo
        StompBookClub bookClub = StompBookClub.getInstance(); //one shared object
// you can use any server...
        Server.threadPerClient(
                1234, //port
                () -> new StompMessagingProtocolImpl<>(bookClub), //protocol factory
                StompMsgEncoderDecoder::new //message encoder decoder factory
        ).serve();

//        Server.reactor(
//                Runtime.getRuntime().availableProcessors(),
//                7777, //port
//        () -> new StompMessagingProtocolImpl<>(bookClub), //protocol factory
//                StompMsgEncoderDecoder::new //message encoder decoder factory
//        ).serve();
    }


}
