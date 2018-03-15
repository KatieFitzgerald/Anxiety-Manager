package com.example.katiefitzgerald.anxietymanager.model;

import java.io.Serializable;

/**
 * Created by Katie Fitzgerald on 05/03/2018.
 */

public class QuestionnaireDao implements Serializable {

    String Questionnaire_ID;
    String User_ID;
    String Subject_ID;
    String Thought_ID;
    String Physical_ID;
    String Mood_ID1;
    String Mood_ID2;
    String Reaction_ID;
    int Mood_Rating1;
    int Mood_Rating2;
    int Overall_Rating;

    public QuestionnaireDao(){

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

    public String getSubject_ID() {
        return Subject_ID;
    }

    public void setSubject_ID(String subject_ID) {
        Subject_ID = subject_ID;
    }

    public String getThought_ID() {
        return Thought_ID;
    }

    public void setThought_ID(String thought_ID) {
        Thought_ID = thought_ID;
    }

    public String getPhysical_ID() {
        return Physical_ID;
    }

    public void setPhysical_ID(String physical_ID) {
        Physical_ID = physical_ID;
    }

    public String getMood_ID1() {
        return Mood_ID1;
    }

    public void setMood_ID1(String mood_ID1) {
        Mood_ID1 = mood_ID1;
    }

    public String getMood_ID2() {
        return Mood_ID2;
    }

    public void setMood_ID2(String mood_ID2) {
        Mood_ID2 = mood_ID2;
    }

    public String getReaction_ID() {
        return Reaction_ID;
    }

    public void setReaction_ID(String reaction_ID) {
        Reaction_ID = reaction_ID;
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
