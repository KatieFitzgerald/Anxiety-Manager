package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Katie Fitzgerald on 17/02/2018.
 */

public class ReactActivity extends AppCompatActivity {

    Button done;

    ImageView stayed;
    ImageView left;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);

        stayed = findViewById(R.id.imageView11);
        left = findViewById(R.id.imageView12);

        stayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stayed.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setImageResource(R.drawable.chosen);
                questionCount += 1;
                checkQuestions();
            }
        });

        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
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