package com.example.katiefitzgerald.anxietymanager.model;

/**
 * Created by Katie Fitzgerald on 28/11/2017.
 */

public class UserDao {

    String User_ID;
    String userEmail;
    String password;

    public UserDao() {

    }

    public UserDao(String user_ID, String userEmail, String password) {
        this.User_ID = user_ID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserID() {
        return User_ID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
