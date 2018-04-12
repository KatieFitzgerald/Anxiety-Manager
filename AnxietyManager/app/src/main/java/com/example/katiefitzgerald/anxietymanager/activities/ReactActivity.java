package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.Questionnaire;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Katie Fitzgerald on 17/02/2018.
 */

public class ReactActivity extends AppCompatActivity {

    Button done;

    ImageView stayed;
    ImageView left;

    String[] questionnaireValues = new String[12];

    int questionCount = 0;

    DatabaseReference QuestionnaireDB = FirebaseDatabase.getInstance().getReference("questionnaire");

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);

        Bundle extras = getIntent().getExtras();
        questionnaireValues = extras.getStringArray("questionnaireObj");

        stayed = findViewById(R.id.stay);
        left = findViewById(R.id.left);

        stayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stayed.setImageResource(R.drawable.chosen);
                questionnaireValues[10] = "Stayed";
                questionCount += 1;
                checkQuestions();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setImageResource(R.drawable.chosen);
                questionnaireValues[10] = "Left";
                questionCount += 1;
                checkQuestions();
            }
        });

        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String questionnaire_id = QuestionnaireDB.push().getKey();

                Questionnaire questionnaire = new Questionnaire(questionnaire_id, questionnaireValues[0], questionnaireValues[1], questionnaireValues[2],
                                                                questionnaireValues[3], questionnaireValues[4], questionnaireValues[5], questionnaireValues[6],
                                                                questionnaireValues[7], questionnaireValues[8], questionnaireValues[9], questionnaireValues[10], questionnaireValues[11]);

               QuestionnaireDB.child(questionnaire_id).setValue(questionnaire);

                Intent calendar = new Intent(getApplicationContext(), CalendarActivity.class);
                calendar.putExtra("user_id", questionnaireValues[1]);
                startActivity(calendar);
                finish();
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