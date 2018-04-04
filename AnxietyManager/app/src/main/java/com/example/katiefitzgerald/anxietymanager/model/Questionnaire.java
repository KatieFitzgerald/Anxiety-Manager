package com.example.katiefitzgerald.anxietymanager.model;

import java.io.Serializable;

/**
 * Created by Katie Fitzgerald on 05/03/2018.
 */

public class Questionnaire implements Serializable {

    private String questionnaire_id;
    private String sensed_id;
    private String user_id;
    private String subject;
    private String thought;
    private String physical;
    private String mood1;
    private String mood2;
    private String reaction;
    private String mood_rating1;
    private String mood_rating2;
    private String overall_rating;
    private String timestamp;

    public Questionnaire() {

    }


    public Questionnaire(String questionnaire_id, String sensed_id, String user_id, String subject,
                         String thought, String physical, String mood1, String mood2, String mood_rating1, String mood_rating2,
                         String overall_rating, String reaction, String timestamp){

        this.questionnaire_id = questionnaire_id;
        this.sensed_id = sensed_id;
        this.user_id = user_id;
        this.subject = subject;
        this.thought = thought;
        this.physical = physical;
        this.mood1 = mood1;
        this.mood2 = mood2;
        this.mood_rating1 = mood_rating1;
        this.mood_rating2 = mood_rating2;
        this.overall_rating = overall_rating;
        this.reaction = reaction;
        this.timestamp = timestamp;

    }

    public String getQuestionnaire_ID() {
        return questionnaire_id;
    }

    public String getSensed_ID() { return sensed_id; }

    public String getUser_ID() {
        return user_id;
    }

    public String getSubject() {
        return subject;
    }

    public String getThought() {
        return thought;
    }

    public String getPhysical() {
        return physical;
    }

    public String getMood1() {
        return mood1;
    }

    public String getMood2() {
        return mood2;
    }

    public String getReaction() {
        return reaction;
    }

    public String getMoodRating1() {
        return mood_rating1;
    }

    public String getMoodRating2() {
        return mood_rating2;
    }

    public String getOverallRating() {
        return overall_rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
