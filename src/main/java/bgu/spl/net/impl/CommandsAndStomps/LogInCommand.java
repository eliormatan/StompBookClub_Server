package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class LogInCommand implements StompFrames<StompBookClub> {
    private String userName;
    private String passWord;
    public LogInCommand(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    @Override
    public void execute(StompBookClub arg) {
        arg.login(userName,passWord);
    }

}
