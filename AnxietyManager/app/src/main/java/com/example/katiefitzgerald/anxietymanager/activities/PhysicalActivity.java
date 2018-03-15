package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.katiefitzgerald.anxietymanager.R;


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
    ImageView headache;

    ImageButton nextQuestion;
    ImageButton previousQuestion;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        breathing = findViewById(R.id.breathingIcon);
        heartbeat = findViewById(R.id.heartbeatIcon);
        sweating = findViewById(R.id.sweatingIcon);
        warm = findViewById(R.id.warmIcon);
        nervousStomach = findViewById(R.id.nervousIcon);
        frequentToilet = findViewById(R.id.toiletIcon);
        shaking = findViewById(R.id.shakingIcon);
        changeAppetite = findViewById(R.id.hungerIcon);
        headache = findViewById(R.id.headacheIcon);

        breathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breathing.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        heartbeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartbeat.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        sweating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sweating.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warm.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        nervousStomach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nervousStomach.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        frequentToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frequentToilet.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        shaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shaking.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        changeAppetite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAppetite.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        headache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headache.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

       /* previousQuestion = findViewById(R.id.previous);
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Thoughts = new Intent(getApplicationContext(), ThoughtsActivity.class);
                startActivity(Thoughts);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });*/
    }

    void checkQuestions() {
        if (questionCount < 1) {
            //do nothing
        }
        else {
            //allow no other options to be chosen
            breathing.setClickable(false);
            heartbeat.setClickable(false);
            sweating.setClickable(false);
            warm.setClickable(false);
            nervousStomach.setClickable(false);
            frequentToilet.setClickable(false);
            shaking.setClickable(false);
            changeAppetite.setClickable(false);
            headache.setClickable(false);


            Intent Emotion = new Intent(getApplicationContext(), MoodActivity.class);
            startActivity(Emotion);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

//            /*nextQuestion = findViewById(R.id.next);
//            nextQuestion.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent Emotion = new Intent(getApplicationContext(), MoodActivity.class);
//                    startActivity(Emotion);
//                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
//
//                }
//            });*/

        }
    }
}