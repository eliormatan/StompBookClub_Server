package bgu.spl.net.api;

import bgu.spl.net.impl.CommandsAndStomps.*;
import bgu.spl.net.impl.bookclub.StompBookClub;
import bgu.spl.net.impl.rci.Command;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StompMsgEncoderDecoder implements MessageEncoderDecoder<StompFrames> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    @Override
    public StompFrames decodeNextByte(byte nextByte) {
        if (nextByte == '\u0000') {

        }
        pushByte(nextByte);
        return null; //not a full object yet
    }

    @Override
    public byte[] encode(StompFrames frame) {
        String encodedCommand = new String();
        return (encodedCommand + "\n" + "\u0000").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

}
