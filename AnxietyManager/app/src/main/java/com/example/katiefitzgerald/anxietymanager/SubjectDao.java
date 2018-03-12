package com.example.katiefitzgerald.anxietymanager;

/**
 * Created by Katie Fitzgerald on 06/03/2018.
 */

public class SubjectDao {

    String subject_ID;
    String subject_Name;

    public SubjectDao() {

    }

    public SubjectDao(String Subject_ID, String Subject_Name) {
        this.subject_ID = Subject_ID;
        this.subject_Name = Subject_Name;
    }

    public String getSubjectID() {
        return subject_ID;
    }

    public String getSubjectName() {
        return subject_Name;
    }

}
