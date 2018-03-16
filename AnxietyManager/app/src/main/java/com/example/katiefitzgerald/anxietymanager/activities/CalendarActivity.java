package com.example.katiefitzgerald.anxietymanager.activities;

/**
 * Created by Katie Fitzgerald on 15/03/2018.
 */

import android.graphics.Color;
import android.os.Bundle;
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
}