package com.example.katiefitzgerald.anxietymanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.katiefitzgerald.anxietymanager.R;

/**
 * Created by Katie Fitzgerald on 17/02/2018.
 */

public class ReactActivity extends AppCompatActivity {

    Button done;

    ImageView stayed;
    ImageView left;

    String[] questionnaire = new String[10];

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");

        stayed = findViewById(R.id.stay);
        left = findViewById(R.id.left);

        stayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stayed.setImageResource(R.drawable.chosen);
                questionnaire[9] = "Stayed";
                questionCount += 1;
                checkQuestions();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setImageResource(R.drawable.chosen);
                questionnaire[9] = "Left";
                questionCount += 1;
                checkQuestions();
            }
        });

        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < questionnaire.length; i++){
                    Log.v("this","This is contents of array " + questionnaire[i]);
                }
            }
        });

    }

    void checkQuestions() {
        if (questionCount < 1) {
            //do nothing
        }
        else {
            stayed.setClickable(false);
            left.setClickable(false);
        }
    }

}