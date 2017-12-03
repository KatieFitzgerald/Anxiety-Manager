package com.example.katiefitzgerald.anxietymanager;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class AnxietyEpisode {
    private String date;
    private String time;
    private String location;
    private String user_id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    @Override
    public String toString() {
        return "" + this.date.toString() + " " + this.time.toString();
    }
}
