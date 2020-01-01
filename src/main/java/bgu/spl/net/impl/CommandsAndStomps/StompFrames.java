package bgu.spl.net.impl.CommandsAndStomps;

import java.io.Serializable;

public interface StompFrames<T> extends Serializable {

    Serializable execute(T arg);
}
