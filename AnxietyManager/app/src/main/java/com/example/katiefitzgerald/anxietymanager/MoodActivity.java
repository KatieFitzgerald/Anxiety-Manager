package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Katie Fitzgerald on 26/01/2018.
 */

public class MoodActivity extends AppCompatActivity {

    ImageButton nextQuestion;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Thoughts = new Intent(getApplicationContext(), RateMoodActivity.class);
                startActivity(Thoughts);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
            }
        });

    }

}
