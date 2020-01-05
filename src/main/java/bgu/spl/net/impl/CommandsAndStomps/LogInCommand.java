package bgu.spl.net.impl.CommandsAndStomps;

import bgu.spl.net.impl.bookclub.StompBookClub;

import java.io.Serializable;

public class LogInCommand extends BaseStompFrame {
    private String userName;
    private String passWord;
    private int connectionID;
    private String version;
    public LogInCommand(String userName, String passWord, int connectionID, String version){
        this.userName = userName;
        this.passWord = passWord;
        this.connectionID = connectionID;
        this.version = version;
    }

    @Override
    public StompFrames run() {
        int ans = bookClub.login(userName,passWord,connectionID);
        switch (ans){
            case 0:
            {
                //Complete Frame to the user
                return new ConnectedFrame(version);
            }
            case 1:
            {
                return new ErrorFrame("User Already Logged IN",bookClub.getGlobalID());
                //Error Frame to the user - Already Logged IN
            }
            case 2:
            {
                return new ErrorFrame("User Has Wrong Password",bookClub.getGlobalID());
                //Error Frame to the user - Wrong PASS
            }
        }
        return null;
    }
}
