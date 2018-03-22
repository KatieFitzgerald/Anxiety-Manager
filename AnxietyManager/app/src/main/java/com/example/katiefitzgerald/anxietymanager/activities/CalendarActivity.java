package com.example.katiefitzgerald.anxietymanager.activities;

/**
 * Created by Katie Fitzgerald on 15/03/2018.
 */

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    TextView month;
    CompactCalendarView compactCalendar;
    String user_id;

    SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    SimpleDateFormat dateFormatDay = new SimpleDateFormat("E, d MMM yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");

        addToAppCalendar();

//        addToDeviceCalendar();
    }

    private void addToAppCalendar() {

        month = findViewById(R.id.monthText);
        compactCalendar = findViewById(R.id.compactcalendar_view);

        DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query sensedAnxiety = sensedDB.child("sensed_anxiety").orderByChild("user_ID").equalTo(user_id);

        sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {

                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                        Log.v("THIS", "THIS " + dsp);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);

        Event ev1 = new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode");
        compactCalendar.addEvent(ev1);

        Event ev2 = new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode");
        compactCalendar.addEvent(ev2);

        // define a listener to receive callbacks when certain events happen.
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                List<Event> events = compactCalendar.getEvents(dateClicked);

                if (events.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "No anxiety sensed on " + dateFormatDay.format(dateClicked), Toast.LENGTH_SHORT).show();

                }
                else {



                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }

        });
    }

    private void addToDeviceCalendar(){

        ContentResolver cr = this.getContentResolver();

        ContentValues values = new ContentValues();

        values.put(CalendarContract.Events.DTSTART, System.currentTimeMillis());
        values.put(CalendarContract.Events.TITLE, "Sensed Anxiety - AM");
        values.put(CalendarContract.Events.DESCRIPTION, "Anxiety Episode sensed");
        values.put(CalendarContract.Events.EVENT_COLOR, Color.BLUE);


        TimeZone currentTimeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, currentTimeZone.getID());

        // Default calendar
        values.put(CalendarContract.Events.CALENDAR_ID, 1);

        long endTime = System.currentTimeMillis() + 1800000;
        values.put(CalendarContract.Events.DTEND, endTime);


        //https://developer.android.com/training/permissions/requesting.html

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
    }
}