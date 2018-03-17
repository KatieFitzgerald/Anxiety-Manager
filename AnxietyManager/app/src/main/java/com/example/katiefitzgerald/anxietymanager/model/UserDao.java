package com.example.katiefitzgerald.anxietymanager.model;

/**
 * Created by Katie Fitzgerald on 28/11/2017.
 */

public class UserDao {

    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean counsellor;

    public UserDao() {
    }

    public UserDao(String id, String name, String email, String password, Boolean counsellor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.counsellor = counsellor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getCounsellor() { return counsellor; }
}
