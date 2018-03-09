package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 26/01/2018.
 */

public class MoodActivity extends AppCompatActivity {

    ImageView afraid;
    ImageView angry;
    ImageView sad;
    ImageView lonely;
    ImageView disgust;
    ImageView embarrassed;
    ImageView distracted;
    ImageView annoyed;
    ImageView nervous;

    String[] chosenMood = new String[2];

    ImageButton nextQuestion;
    ImageButton previousQuestion;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        afraid = findViewById(R.id.afraid);
        angry = findViewById(R.id.angry);
        sad = findViewById(R.id.sad);
        lonely = findViewById(R.id.lonely);
        disgust = findViewById(R.id.disgusted);
        embarrassed = findViewById(R.id.embarrassed);
        distracted = findViewById(R.id.distracted);
        annoyed = findViewById(R.id.annoyed);
        nervous = findViewById(R.id.nervous);

        afraid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afraid.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Afraid";
                }
                else {
                    chosenMood[1] = "Afraid";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angry.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Angry";
                }
                else {
                    chosenMood[1] = "Angry";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sad.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Sad";
                }
                else {
                    chosenMood[1] = "Sad";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        lonely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lonely.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Lonely";
                }
                else {
                    chosenMood[1] = "Lonely";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        disgust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disgust.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Disgust";
                }
                else {
                    chosenMood[1] = "Disgust";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        embarrassed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                embarrassed.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Embarrassed";
                }
                else {
                    chosenMood[1] = "Embarrassed";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        distracted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distracted.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Distracted";
                }
                else {
                    chosenMood[1] = "Distracted";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        annoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annoyed.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Annoyed";
                }
                else {
                    chosenMood[1] = "Annoyed";
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        nervous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nervous.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Nervous";
                }
                else {
                    chosenMood[1] = "Nervous";
                }
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
        if (questionCount < 2) {
            //do nothing, moods chosen
        }
        else {
            //allow no other options to be chosen
            afraid.setClickable(false);
            angry.setClickable(false);
            sad.setClickable(false);
            lonely.setClickable(false);
            disgust.setClickable(false);
            embarrassed.setClickable(false);
            distracted.setClickable(false);
            annoyed.setClickable(false);
            nervous.setClickable(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent RateMood = new Intent(getApplicationContext(), RateMoodActivity.class);
                    RateMood.putExtra("chosenMoods", chosenMood);
                    startActivity(RateMood);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
                }
            }, 1000);

            /*nextQuestion = findViewById(R.id.next);
            nextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent RateMood = new Intent(getApplicationContext(), RateMoodActivity.class);
                    RateMood.putExtra("chosenMoods", chosenMood);
                    startActivity(RateMood);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
                }
            });*/

        }
    }

}
