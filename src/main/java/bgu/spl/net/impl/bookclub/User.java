package bgu.spl.net.impl.bookclub;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class User {

    private int uniqueId;
    private String username;
    private String password;
    private AtomicBoolean login;

    public User(String username,String password){
        this.username=username;
        this.password=password;
        this.login=new AtomicBoolean(false);
        uniqueId=-1;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogin() {
        return login.get();
    }

    public void setLogin(boolean login) { this.login.set(login);
    }

 }


