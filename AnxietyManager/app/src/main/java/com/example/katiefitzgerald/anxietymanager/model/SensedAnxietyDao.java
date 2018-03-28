package com.example.katiefitzgerald.anxietymanager.model;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class SensedAnxietyDao {

    private String sensedID;
    private String timestamp;
    private String location;
    private String user_ID;

    public SensedAnxietyDao() {

    }

    public SensedAnxietyDao(String sensed_ID, String timestamp, String location, String user_ID) {
        this.sensedID = sensed_ID;
        this.timestamp = timestamp;
        this.location = location;
        this.user_ID = user_ID;

    }

    public String getSensedID() {
        return sensedID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public String toString() {
        return this.timestamp + " " + this.location;

    }



}
