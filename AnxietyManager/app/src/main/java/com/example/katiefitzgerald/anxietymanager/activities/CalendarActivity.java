package com.example.katiefitzgerald.anxietymanager.activities;

/**
 * Created by Katie Fitzgerald on 15/03/2018.
 */

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    TextView month;
    CompactCalendarView compactCalendar;

    SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    SimpleDateFormat dateFormatDay = new SimpleDateFormat("E, d MMM yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        month = findViewById(R.id.monthText);
        compactCalendar = findViewById(R.id.compactcalendar_view);

        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);

        Event ev1 = new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode");
        compactCalendar.addEvent(ev1);

        Event ev2 = new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode");
        compactCalendar.addEvent(ev2);

        addToDeviceCalendar();

        // define a listener to receive callbacks when certain events happen.
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                List<Event> events = compactCalendar.getEvents(dateClicked);
                if (events.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "No anxiety sensed on " + dateFormatDay.format(dateClicked), Toast.LENGTH_SHORT).show();

                }
                else {
                    //do something
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
        values.put(CalendarContract.Events.TITLE, "Sensed Anxiety - Anxiety Manager");
        values.put(CalendarContract.Events.DESCRIPTION, "Something");
        values.put(CalendarContract.Events.EVENT_COLOR, Color.BLUE);


        TimeZone currentTimeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, currentTimeZone.getID());

        // Default calendar
        values.put(CalendarContract.Events.CALENDAR_ID, 1);

        long endTime = System.currentTimeMillis() + 1800000;
        values.put(CalendarContract.Events.DTEND, endTime);


        //https://developer.android.com/training/permissions/requesting.html

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }

        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
    }
}