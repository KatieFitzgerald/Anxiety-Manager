package com.example.katiefitzgerald.anxietymanager.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.katiefitzgerald.anxietymanager.model.UserDao;

import java.sql.SQLException;

/**
 * Created by Katie Fitzgerald on 07/03/2018.
 */

public class DatabaseManager {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "AnxietyManager";

    //define table names
    private static final String TABLE_QUESTIONNAIRE = "Questionnaire";
    private static final String TABLE_USER = "User_Profile";
    private static final String TABLE_SUBJECT = "Subject";
    private static final String TABLE_THOUGHT = "Thought";
    private static final String TABLE_PHYSICAL = "Physical";
    private static final String TABLE_MOOD = "Mood";
    private static final String TABLE_REACTION = "Reaction";

    //Subject table column names
    private static final String KEY_SUBJECT_NAME = "SubjectName";

    //Thought table column names
    private static final String KEY_THOUGHT_NAME = "ThoughtName";

    //Physical table column names
    private static final String KEY_PHYSICAL_NAME = "PhysicalName";

    //React table column names
    private static final String KEY_REACTION_NAME = "ReactionName";

    //Mood table columns names
    private static final String KEY_MOOD_NAME = "MoodName";
    private static final String KEY_MOOD__USER_ADDED = "UserAdded";

    //User table column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_PASSWORD = "password";

    //Create tables
    private static final String CREATE_SUBJECT_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "(_id INTEGER PRIMARY KEY autoincrement not null, SubjectName TEXT not null);";
    private static final String CREATE_THOUGHT_TABLE = "CREATE TABLE " + TABLE_THOUGHT + "(_id INTEGER PRIMARY KEY autoincrement not null, ThoughtName TEXT not null);";
    private static final String CREATE_PHYSICAL_TABLE = "CREATE TABLE " + TABLE_PHYSICAL + "(_id INTEGER PRIMARY KEY autoincrement not null, PhysicalName TEXT not null);";
    private static final String CREATE_MOOD_TABLE = "CREATE TABLE " + TABLE_MOOD + "(_id INTEGER PRIMARY KEY autoincrement not null, MoodName TEXT not null, UserAdded TEXT);";
    private static final String CREATE_REACT_TABLE = "CREATE TABLE " + TABLE_REACTION + "(_id INTEGER PRIMARY KEY autoincrement not null, ReactionName TEXT not null);";
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(user_id INTEGER PRIMARY KEY autoincrement, user_name TEXT, user_email TEXT, password TEXT);";


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
            db.execSQL(CREATE_THOUGHT_TABLE);
            db.execSQL(CREATE_PHYSICAL_TABLE);
            db.execSQL(CREATE_MOOD_TABLE);
            db.execSQL(CREATE_REACT_TABLE);
            db.execSQL(CREATE_USER_TABLE);

            ContentValues mood = new ContentValues();

            mood.put(KEY_MOOD_NAME, "Confused");
            mood.put(KEY_MOOD__USER_ADDED, "true");
            db.insert(TABLE_MOOD, null, mood);

            insertDefaultSubjects(db);
            insertDefaultThoughts(db);
            insertPhysicalFeelings(db);
            insertMoods(db);
            insertReactions(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THOUGHT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHYSICAL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REACTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

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

        private void insertDefaultThoughts(SQLiteDatabase db) {

            //insert static data to subject table
            ContentValues chooseThought = new ContentValues();

            chooseThought.put(KEY_THOUGHT_NAME, "Too many people in college");
            db.insert(TABLE_THOUGHT, null, chooseThought);

            chooseThought.put(KEY_THOUGHT_NAME, "Not going to have money to go out this weekend");
            db.insert(TABLE_THOUGHT, null, chooseThought);

            chooseThought.put(KEY_THOUGHT_NAME, "Having to sit on the bus beside a stranger");
            db.insert(TABLE_THOUGHT, null, chooseThought);

            chooseThought.put(KEY_THOUGHT_NAME, "Going to fail my assignment");
            db.insert(TABLE_THOUGHT, null, chooseThought);

        }

        private void insertPhysicalFeelings(SQLiteDatabase db) {

            //insert static data to subject table
            ContentValues physical = new ContentValues();

            physical.put(KEY_PHYSICAL_NAME, "unusual breathing");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "unusual heartbeat");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "feeling hot");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "excessive sweating");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "excessive shaking");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "frequent toilet trips");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "unusual appetite");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "nervous stomach");
            db.insert(TABLE_PHYSICAL, null, physical);

            physical.put(KEY_PHYSICAL_NAME, "headache");
            db.insert(TABLE_PHYSICAL, null, physical);

        }

        private void insertMoods(SQLiteDatabase db) {

            //insert static data to subject table
            ContentValues mood = new ContentValues();

            mood.put(KEY_MOOD_NAME, "afraid");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "angry");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "sad");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "lonely");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "disgust");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "embarrassed");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "distracted");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "annoyed");
            db.insert(TABLE_MOOD, null, mood);

            mood.put(KEY_MOOD_NAME, "nervous");
            db.insert(TABLE_MOOD, null, mood);

        }

        private void insertReactions(SQLiteDatabase db){

            //insert static data to subject table
            ContentValues reaction = new ContentValues();

            reaction.put(KEY_REACTION_NAME, "stayed");
            db.insert(TABLE_REACTION, null, reaction);

            reaction.put(KEY_REACTION_NAME, "left");
            db.insert(TABLE_REACTION, null, reaction);

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

    //insert into request table from user input
    public void insertThought(String thoughtName) {

        ContentValues thought = new ContentValues();

        thought.put(KEY_THOUGHT_NAME, thoughtName);
        db.insert(TABLE_THOUGHT, null, thought);

    }

    //returns subjects
    public Cursor selectThoughts()
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM Thought;", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    //insert into request table from user input
    public void insertMood(String moodName) {

        ContentValues mood = new ContentValues();

        mood.put(KEY_MOOD_NAME, moodName);
        mood.put(KEY_MOOD__USER_ADDED, "true");
        db.insert(TABLE_MOOD, null, mood);

    }

    //returns subjects
    public Cursor selectMood()
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM Mood WHERE UserAdded = 'true';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    public void addUser(UserDao user) {

        ContentValues userValues = new ContentValues();
        userValues.put(KEY_USER_NAME, user.getName());
        userValues.put(KEY_USER_EMAIL, user.getEmail());
        userValues.put(KEY_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, userValues);
        db.close();

    }

    public void updateUser(UserDao user) {
        //SQLiteDatabase db = this.getWritableDatabase();

        ContentValues userValues = new ContentValues();
        userValues.put(KEY_USER_NAME, user.getName());
        userValues.put(KEY_USER_EMAIL, user.getEmail());
        userValues.put(KEY_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, userValues, KEY_USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    //Code snippet from http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/
    public boolean checkUser(String email, String password) {

        db = DBHelper.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM User_Profile WHERE user_name = '" + email + "' AND password = '" + password + "';", null );

        if((mCursor != null) && (mCursor.getCount() > 0)) {
            return true;
        }

        return false;

    }


}