package com.example.katiefitzgerald.anxietymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Katie Fitzgerald on 26/01/2018.
 */

public class PhysicalActivity extends AppCompatActivity {

    ImageView breathing;
    ImageView heartbeat;
    ImageView sweating;
    ImageView warm;
    ImageView nervousStomach;
    ImageView frequentToilet;
    ImageView shaking;
    ImageView changeAppetite;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        breathing = findViewById(R.id.breathingIcon);
        heartbeat = findViewById(R.id.heartbeatIcon);
        sweating = findViewById(R.id.sweatingIcon);
        warm = findViewById(R.id.warmIcon);
        nervousStomach = findViewById(R.id.nervousIcon);
        frequentToilet = findViewById(R.id.toilet);
        shaking = findViewById(R.id.shakingIcon);
        changeAppetite = findViewById(R.id.hungerIcon);

        breathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Breathing", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        heartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Heartbeat", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        sweating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Sweating", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Warmth", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        nervousStomach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Stomach", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        frequentToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toilet", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        shaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Shaking", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

        changeAppetite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Appetite", Toast.LENGTH_SHORT).show();
                questionCount += 1;
                checkQuestions();
            }
        });

    }

    void checkQuestions() {
        if (questionCount < 2) {
            //do nothing
        }
        else {
            Toast.makeText(getApplicationContext(), "Two feelings choosen", Toast.LENGTH_SHORT).show();

            //allow no other options to be chosen
            breathing.setClickable(false);
            heartbeat.setClickable(false);
            sweating.setClickable(false);
            warm.setClickable(false);
            nervousStomach.setClickable(false);
            frequentToilet.setClickable(false);
            shaking.setClickable(false);
            changeAppetite.setClickable(false);
        }
    }
}