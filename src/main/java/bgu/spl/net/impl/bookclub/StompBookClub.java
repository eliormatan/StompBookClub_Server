package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.RCIClient;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StompBookClub {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> registerdToGenreMap;
    ConcurrentHashMap<String,String> listOfUsers;
}
