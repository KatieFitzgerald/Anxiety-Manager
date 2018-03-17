package com.example.katiefitzgerald.anxietymanager.model;

/**
 * Created by Katie Fitzgerald on 20/11/2017.
 */

public class SensedAnxietyDao {

    private String Sensed_ID;
    private String Timestamp;
    private String Location;
    private String User_ID;

    public SensedAnxietyDao() {

    }

    public SensedAnxietyDao(String Sensed_ID, String Timestamp, String Location, String User_ID) {
        this.Sensed_ID = Sensed_ID;
        this.Timestamp = Timestamp;
        this.Location = Location;
        this.User_ID = User_ID;

    }

    public String getSensedID() {
        return Sensed_ID;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public String getLocation() {
        return Location;
    }

    public String getUser_ID() {
        return User_ID;
    }



}
