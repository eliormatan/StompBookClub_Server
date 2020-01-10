package bgu.spl.net.impl.bookclub;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class User {

    private int uniqueId;
    private String username;
    private String password;
    private boolean login;
    private ConcurrentHashMap<String, ArrayList<Book>> booksInventory;

    public User(String username,String password){
        this.username=username;
        this.password=password;
        this.login=false;
        booksInventory=new ConcurrentHashMap<>();
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
        return login;
    }

    public ConcurrentHashMap<String, ArrayList<Book>> getBooksInventory() {
        return booksInventory;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

 }


