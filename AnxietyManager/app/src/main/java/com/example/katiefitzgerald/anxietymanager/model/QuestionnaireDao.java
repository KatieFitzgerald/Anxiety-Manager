package com.example.katiefitzgerald.anxietymanager.model;

import java.io.Serializable;

/**
 * Created by Katie Fitzgerald on 05/03/2018.
 */

public class QuestionnaireDao implements Serializable {

    String Questionnaire_ID;
    String User_ID;
    String Subject;
    String Thought;
    String Physical;
    String Mood1;
    String Mood2;
    String Reaction;
    int Mood_Rating1;
    int Mood_Rating2;
    int Overall_Rating;


    public QuestionnaireDao(){

    }


    public QuestionnaireDao(String Questionnaire_ID, String User_ID, String Subject, String Thought,
                            String Physical, String Mood1, String Mood2, String Reaction,
                            int Mood_Rating1, int Mood_Rating2, int Overall_Rating){

        this.Questionnaire_ID = Questionnaire_ID;
        this.User_ID = User_ID;
        this.Subject = Subject;
        this.Thought = Thought;
        this.Physical = Physical;
        this.Mood1 = Mood1;
        this.Mood2 = Mood2;
        this.Mood_Rating1 = Mood_Rating1;
        this.Mood_Rating2 = Mood_Rating2;
        this.Overall_Rating = Overall_Rating;
        this.Reaction = Reaction;

    }

    public String getQuestionnaire_ID() {
        return Questionnaire_ID;
    }

    public void setQuestionnaire_ID(String questionnaire_ID) {
        Questionnaire_ID = questionnaire_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        Subject = Subject;
    }


    public String getThought() {
        return Thought;
    }

    public void setThought(String thought) {
        Thought = thought;
    }

    public String getPhysical() {
        return Physical;
    }

    public void setPhysical(String physical) {
        Physical = physical;
    }

    public String getMood1() {
        return Mood1;
    }

    public void setMood1(String mood1) {
        Mood1 = mood1;
    }

    public String getMood2() {
        return Mood2;
    }

    public void setMood2(String mood2) {
        Mood2 = mood2;
    }

    public String getReaction() {
        return Reaction;
    }

    public void setReaction(String reaction) {
        Reaction = reaction;
    }

    public int getMood_Rating1() {
        return Mood_Rating1;
    }

    public void setMood_Rating1(int mood_Rating1) {
        Mood_Rating1 = mood_Rating1;
    }

    public int getMood_Rating2() {
        return Mood_Rating2;
    }

    public void setMood_Rating2(int mood_Rating2) {
        Mood_Rating2 = mood_Rating2;
    }

    public int getOverall_Rating() {
        return Overall_Rating;
    }

    public void setOverall_Rating(int overall_Rating) {
        Overall_Rating = overall_Rating;
    }
}
