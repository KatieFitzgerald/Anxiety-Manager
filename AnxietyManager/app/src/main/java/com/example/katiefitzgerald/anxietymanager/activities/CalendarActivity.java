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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.SensedAnxiety;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    TextView month;
    CompactCalendarView compactCalendar;
    String user_id;
    View questionnaireDetails;

    SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMM yyyy, H:m", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");

        questionnaireDetails = findViewById(R.id.questionnaireDetails);
        questionnaireDetails.setVisibility(View.INVISIBLE);

        addToAppCalendar();

//        addToDeviceCalendar();
    }

    private void addToAppCalendar() {

        month = findViewById(R.id.monthText);
        compactCalendar = findViewById(R.id.compactcalendar_view);

        DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query sensedAnxiety = sensedDB.child("sensed_anxiety").orderByChild("user_ID").equalTo(user_id);

        final ArrayList<Long> timestamps = null;

        sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {

                    for (DataSnapshot anxietyData : dataSnapshot.getChildren()) {

                        SensedAnxiety sensedAnxiety = anxietyData.getValue(SensedAnxiety.class);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);

        compactCalendar.addEvents(getEvents());

        // define a listener to receive when data click or month swipe occurs
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {

                List<Event> events = compactCalendar.getEvents(dateClicked);

                if (events.isEmpty()) {

                    questionnaireDetails.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "No anxiety sensed on " + dateFormatDay.format(dateClicked), Toast.LENGTH_SHORT).show();
                }
                else {
                    questionnaireDetails.setVisibility(View.VISIBLE);

                    //fill in default responses for demonstration
                    TextView location = questionnaireDetails.findViewById(R.id.location);
                    location.setText("- Kevin St");
                    TextView subjectTV = questionnaireDetails.findViewById(R.id.subject);
                    subjectTV.setText("- Exam stress");
                    TextView physicalTV = questionnaireDetails.findViewById(R.id.physical);
                    physicalTV.setText("- Shaking");
                    TextView thoughtTV = questionnaireDetails.findViewById(R.id.thought);
                    thoughtTV.setText("- I don't have enough time to study everything");
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                month.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }

        });
    }

    private ArrayList<Event> getEvents() {

        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode"));
        events.add(new Event(Color.RED, System.currentTimeMillis(), "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521747297801l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521564300000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521804979122l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521813159970l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521482040000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521891276134l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1522673069436l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520467200000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520467200000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520121600000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1522364400000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520985600000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520985600000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1520985600000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521072000000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521072000000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521072000000l, "Anxiety Episode"));
        events.add(new Event(Color.RED, 1521158400000l, "Anxiety Episode"));

        return events;
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