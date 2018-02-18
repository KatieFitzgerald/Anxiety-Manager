package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Katie Fitzgerald on 17/02/2018.
 */

public class ReactActivity extends AppCompatActivity {

    ImageButton previousQuestion;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);

        previousQuestion = findViewById(R.id.previous);
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Thoughts = new Intent(getApplicationContext(), MoodActivity.class);
                startActivity(Thoughts);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });

    }

}