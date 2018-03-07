package com.example.katiefitzgerald.anxietymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Katie Fitzgerald on 07/03/2018.
 */

public class DatabaseManager {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "AnxietyManager";

    //define table names
    private static final String TABLE_QUESTIONNAIRE = "Questionnaire";
    private static final String TABLE_SUBJECT = "Subject";
    private static final String TABLE_THOUGHT = "Thought";
    private static final String TABLE_PHYSICAL = "Physical";
    private static final String TABLE_MOOD = "Mood";
    private static final String TABLE_REACTION = "Reaction";

    //Subject table column names
    private static final String KEY_SUBJECT_NAME = "SubjectName";

    private static final String CREATE_SUBJECT_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "(_id INTEGER PRIMARY KEY autoincrement not null, SubjectName TEXT not null);";

    private final Context context;
    private MyDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) {
        this.context = ctx;
        DBHelper = new MyDatabaseHelper(context);
    }

    private static class MyDatabaseHelper extends SQLiteOpenHelper {

        public MyDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_SUBJECT_TABLE);

            insertDefaultSubjects(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);

            onCreate(db);

        }

        private void insertDefaultSubjects(SQLiteDatabase db) {

            //insert static data to subject table
            ContentValues chooseSubject = new ContentValues();

            chooseSubject.put(KEY_SUBJECT_NAME, "College");
            db.insert(TABLE_SUBJECT, null, chooseSubject);

            chooseSubject.put(KEY_SUBJECT_NAME, "Money");
            db.insert(TABLE_SUBJECT, null, chooseSubject);

            chooseSubject.put(KEY_SUBJECT_NAME, "Getting Bus");
            db.insert(TABLE_SUBJECT, null, chooseSubject);

            chooseSubject.put(KEY_SUBJECT_NAME, "Assignment");
            db.insert(TABLE_SUBJECT, null, chooseSubject);

        }

    }

    public DatabaseManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close()
    {
        DBHelper.close();
    }

    //insert into request table from user input
    public void insertSubject(String subjectName) {

        ContentValues subject = new ContentValues();

        subject.put(KEY_SUBJECT_NAME, subjectName);
        db.insert(TABLE_SUBJECT, null, subject);

    }

    //returns subjects
    public Cursor selectSubjects()
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM Subject;", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }





}
