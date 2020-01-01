package bgu.spl.net.impl.bookclub;

import bgu.spl.net.impl.rci.Command;

import java.io.Serializable;

public class LogInCommand implements Command<StompBookClub> {
    private String userName;
    private String passWord;
    public LogInCommand(String userName,String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    @Override
    public Serializable execute(StompBookClub arg) {
        arg.login(userName,passWord);
        return "OK"; //TODO: Know what is Serializable
    }
}
