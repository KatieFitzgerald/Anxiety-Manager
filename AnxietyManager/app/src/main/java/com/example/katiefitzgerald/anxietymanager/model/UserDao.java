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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCounsellor(Boolean counsellor){
        this.counsellor = counsellor;
    }
}
