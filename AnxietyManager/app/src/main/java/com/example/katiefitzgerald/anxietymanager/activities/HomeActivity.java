package com.example.katiefitzgerald.anxietymanager.activities;

//Pie chart tutorial used https://www.youtube.com/watch?v=8BcTXbwDGbg

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.UserDao;
import com.example.katiefitzgerald.anxietymanager.recievers.AnxietyReceiver;
import com.example.katiefitzgerald.anxietymanager.recievers.DailyReceiver;
import com.google.firebase.database.DataSnapshot;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String user;
    Button sensedAnxiety;
    Button addTodayAnxiety;
    Button calendarButton;
    Button cycleButton;
    Button profileButton;
    TextView welcome;
    int counter = 0;

    DatabaseReference usersDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = findViewById(R.id.textView3);

        user = (String) getIntent().getSerializableExtra("user_id");

        usersDB = FirebaseDatabase.getInstance().getReference("user_profile");

        usersDB.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserDao userFound = dataSnapshot.getValue(UserDao.class);

                welcome.setText("Welcome, " + userFound.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DB", "Failed to read value.", error.toException());
            }
        });

        PieChart chart = findViewById(R.id.worriesChart);
        addDataSet(chart);

        dailyNotification();

        addTodayAnxiety = findViewById(R.id.addAnxiety);
        addTodayAnxiety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        addTodayAnxiety.setBackgroundResource(R.drawable.pressed);

                        Intent Questionnaire = new Intent(getApplicationContext(), WhatsUpActivity.class);
                        Questionnaire.putExtra("user_id", user);
                        startActivity(Questionnaire);
                        return true;
                    case MotionEvent.ACTION_UP:
                        addTodayAnxiety.setBackgroundResource(R.drawable.shape);
                        return true;
                    default:
                        return false;
                }
            }
        });

        sensedAnxiety = findViewById(R.id.sensedAnxiety);
        sensedAnxiety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sensedAnxiety.setBackgroundResource(R.drawable.pressed);
                        sensedAnxiety.setTextColor(Color.GRAY);

                        Intent sensedActivity = new Intent(getApplicationContext(), SensedActivity.class);
                        sensedActivity.putExtra("user_id", user);
                        startActivity(sensedActivity);
                        return true;
                    case MotionEvent.ACTION_UP:
                        sensedAnxiety.setBackgroundResource(R.drawable.shape);
                        sensedAnxiety.setTextColor(Color.WHITE);

                        return true;
                    default:
                        return false;
                }
            }
        });


        calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        calendarButton.setBackgroundResource(R.drawable.pressed);
                        calendarButton.setTextColor(Color.GRAY);
                        Intent calendar = new Intent(getApplicationContext(), CalendarActivity.class);
                        calendar.putExtra("user_id", user);
                        startActivity(calendar);

                    case MotionEvent.ACTION_UP:
                        calendarButton.setBackgroundResource(R.drawable.shape);
                        calendarButton.setTextColor(Color.WHITE);
                        return true;
                    default:
                        return false;
                }
            }
        });

        cycleButton = findViewById(R.id.cycleBtn);
        cycleButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cycleButton.setBackgroundResource(R.drawable.pressed);
                        cycleButton.setTextColor(Color.GRAY);
                        Intent cycle = new Intent(getApplicationContext(), InsightActivity.class);
                        startActivity(cycle);

                    case MotionEvent.ACTION_UP:
                        cycleButton.setBackgroundResource(R.drawable.shape);
                        cycleButton.setTextColor(Color.WHITE);

                        return true;
                    default:
                        return false;
                }
            }
        });

        profileButton = findViewById(R.id.profileBtn);
        profileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        profileButton.setBackgroundResource(R.drawable.pressed);
                        profileButton.setTextColor(Color.GRAY);

                    case MotionEvent.ACTION_UP:
                        profileButton.setBackgroundResource(R.drawable.shape);
                        return true;
                    default:
                        return false;
                }
            }
        });

        SensingAnxiety senseAnxiety = new SensingAnxiety();
        senseAnxiety.execute();
    }

    @Override
    public void onBackPressed() {
    }

    public static void addDataSet(PieChart chart) {

        int[] yData = {40, 30, 30};
        String[] anxietyNamesData = {"College", "Social", "Money"};

        ArrayList<PieEntry> yEntries = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntries.add(new PieEntry(yData[i], i));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(12);

        //add color to dataset
        ArrayList<Integer> color = new ArrayList<>();
        color.add(Color.rgb(145, 243, 247));
        color.add(Color.rgb(250, 220, 251));
        color.add(Color.rgb(174, 249, 228));

        pieDataSet.setColors(color);

        List<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < anxietyNamesData.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color.get(i);
            entry.label = anxietyNamesData[i];
            entries.add(entry);
        }

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.rgb(255, 255, 255));
        legend.setCustom(entries);

        PieData pieData = new PieData(pieDataSet);

        chart.setTouchEnabled(false);
        chart.setData(pieData);
        chart.setTransparentCircleRadius(0);
        chart.setEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(0);
        chart.invalidate();

    }

    private class SensingAnxiety extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            final int[] arduinoVals = {12, 13, 12, 14, 26, 12};
            final int threshold = 26;

            //every 10 seconds read value from "ardunio"
            //check if value is above threshold or not
            while(counter < arduinoVals.length){

                if(arduinoVals[counter] >= threshold){
                    sendNotification();
                }

                counter +=1;

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private void sendNotification() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AnxietyReceiver.class);

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }

    private void dailyNotification() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, DailyReceiver.class);

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);

        //set repeating alarm manager once a day
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),alarmManager.INTERVAL_DAY, broadcast);

    }
}
