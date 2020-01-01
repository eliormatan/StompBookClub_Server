package bgu.spl.net.api;

import bgu.spl.net.impl.rci.Command;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StompMsgEncoderDecoder implements MessageEncoderDecoder<Command> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    @Override
    public Command decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        if (nextByte == '\n') {
            return popCommandType();
        }
        pushByte(nextByte);
        return null; //not a full object yet
    }

    @Override
    public byte[] encode(Command frame) {
        String encodedCommand = new String();


        return (encodedCommand + "\n" + "\u0000").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private Command popCommandType() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        switch(result){
            case "SEND":
            {

            }
            case "SUBSCRIBE":
            {

            }
            case "UNSUBSCRIBE":
            {

            }
            case "DISCONNECT":
            {

            }
            case "CONNECT":
            {

            }
        len = 0;
        return null;
    }

}
