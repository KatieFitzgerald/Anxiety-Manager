package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.fragments.SubjectFragment;
import com.example.katiefitzgerald.anxietymanager.model.User;
import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Katie Fitzgerald on 11/04/2018.
 */

public class CounsellorHomeActivity extends AppCompatActivity {

    String user;
    TextView welcome;
    DatabaseReference usersDB;
    Button sensedAnxiety;
    Button calendarButton;
    Button insightButton;
    Button profileButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_user);

        welcome = findViewById(R.id.textView2);

        user = (String) getIntent().getSerializableExtra("user_id");

        usersDB = FirebaseDatabase.getInstance().getReference("user_profile");

        usersDB.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User userFound = dataSnapshot.getValue(User.class);

                welcome.setText(userFound.getName() +"\'s current worries");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DB", "Failed to read value.", error.toException());
            }
        });

        PieChart chart = findViewById(R.id.worriesChart);
        SubjectFragment.addDataSet(chart);

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

        insightButton = findViewById(R.id.insightsBtn);
        insightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        insightButton.setBackgroundResource(R.drawable.pressed);
                        insightButton.setTextColor(Color.GRAY);

                        Intent cycle = new Intent(getApplicationContext(), InsightActivity.class);
                        startActivity(cycle);

                    case MotionEvent.ACTION_UP:

                        insightButton.setBackgroundResource(R.drawable.shape);
                        insightButton.setTextColor(Color.WHITE);

                        return true;
                    default:
                        return false;
                }
            }
        });

        profileButton = findViewById(R.id.lgOutBtn);
        profileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        profileButton.setBackgroundResource(R.drawable.pressed);
                        profileButton.setTextColor(Color.GRAY);

                        Intent home = new Intent(getApplicationContext(), ChoosePatientActivity.class);
                        startActivity(home);

                    case MotionEvent.ACTION_UP:

                        profileButton.setBackgroundResource(R.drawable.shape);
                        profileButton.setTextColor(Color.WHITE);

                        return true;

                    default:
                        return false;
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
    }
}
