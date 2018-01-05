package com.example.katiefitzgerald.anxietymanager;

/**
 * Created by Katie Fitzgerald on 28/11/2017.
 */

public class UserDao {

    String userID;
    String userEmail;
    String password;

    public UserDao() {

    }

    public UserDao(String userID, String userEmail, String password) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
