package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.adapters.MoodDialogAdapter;
import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;

import java.sql.SQLException;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Katie Fitzgerald on 26/01/2018.
 */

public class MoodActivity extends AppCompatActivity {

    ImageView afraid;
    ImageView angry;
    ImageView sad;
    ImageView lonely;
    ImageView worried;
    ImageView embarrassed;
    ImageView distracted;
    ImageView nervous;
    ImageView other;

    MoodDialogAdapter cursorAdapter;

    DatabaseManager db = new DatabaseManager(this);

    String[] chosenMood = new String[2];
    String questionnaire[] = new String[12];

    TextView warning;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");

        afraid = findViewById(R.id.afraid);
        angry = findViewById(R.id.angry);
        sad = findViewById(R.id.sad);
        lonely = findViewById(R.id.lonely);
        worried = findViewById(R.id.worried);
        embarrassed = findViewById(R.id.embarrassed);
        distracted = findViewById(R.id.distracted);
        nervous = findViewById(R.id.nervous);
        other = findViewById(R.id.other);

        afraid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afraid.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Afraid";
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Afraid";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Angry";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Sad";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Lonely";
                    questionnaire[6] = chosenMood[1];
                }
                questionCount += 1;
                checkQuestions();
            }
        });

        worried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worried.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenMood[0] = "Worried";
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Worried";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Embarrassed";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Distracted";
                    questionnaire[6] = chosenMood[1];
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
                    questionnaire[5] = chosenMood[0];
                }
                else {
                    chosenMood[1] = "Nervous";
                    questionnaire[6] = chosenMood[1];

                }
                questionCount += 1;
                checkQuestions();
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder otherDialog = new AlertDialog.Builder(MoodActivity.this);
                final View dialogView = getLayoutInflater().inflate(R.layout.mood_dialog, null);
                final EditText addOther = dialogView.findViewById(R.id.otherEmotion);
                final ListView emotionList = dialogView.findViewById(R.id.emotionList);
                Button add = dialogView.findViewById(R.id.add);

                try {
                    db.open();

                    Cursor moods = db.selectMood();
                    cursorAdapter = new MoodDialogAdapter(MoodActivity.this, moods);
                    emotionList.setAdapter(cursorAdapter);

                    db.close();

                    emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId) {
                            // Get the data associated with selected item

                            Cursor returnedMoodCursor = (Cursor) listView.getItemAtPosition(itemPosition);
                            String moodChosen = returnedMoodCursor.getString(1);

                            if (questionCount == 1) {
                                chosenMood[1] = moodChosen;
                                questionnaire[5] = chosenMood[1];
                                questionCount += 1;
                                checkQuestions();
                            }
                            else {
                                chosenMood[0] = moodChosen;
                                questionnaire[5] = chosenMood[0];
                                questionnaire[6] = "Custom emotion chosen";
                                questionCount = 3;
                                checkQuestions();
                            }
                        }

                    });

                }
                catch (SQLException e) {
                    Toast.makeText(MoodActivity.this, "Error opening database", LENGTH_SHORT).show();
                }

                add.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        final String otherEmotion = addOther.getText().toString();

                        if (!otherEmotion.isEmpty()) {

                            addOther.setText("");
                            try {
                                db.open();

                                db.insertMood(otherEmotion);

                                Cursor moods = db.selectMood();
                                cursorAdapter = new MoodDialogAdapter(MoodActivity.this, moods);
                                cursorAdapter.notifyDataSetChanged();
                                emotionList.setAdapter(cursorAdapter);

                                db.close();

                                if (questionCount == 1) {
                                    chosenMood[1] = otherEmotion;
                                    questionnaire[5] = chosenMood[1];
                                    questionCount += 1;
                                    checkQuestions();
                                }
                                else {
                                    chosenMood[0] = otherEmotion;
                                    questionnaire[5] = chosenMood[0];
                                    questionnaire[6] = "Custom emotion chosen";
                                    questionCount = 3;
                                    checkQuestions();
                                }

                            }
                            catch (SQLException e) {
                                Toast.makeText(MoodActivity.this, "Error inserting into database", LENGTH_SHORT).show();
                            }

                        }
                        else {
                            warning = dialogView.findViewById(R.id.warning);
                            warning.setTextColor(Color.RED);
                            warning.setText("Please enter an emotion");

                            addOther.addTextChangedListener(new TextWatcher()
                            {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                }

                                public void onTextChanged(CharSequence s, int start, int before, int count){
                                    warning.setText("");
                                }

                                @Override
                                public void afterTextChanged(Editable editable) {

                                }
                            });
                        }

                    }

                });

                otherDialog.setView(dialogView);
                AlertDialog createDialog = otherDialog.create();
                createDialog.show();
            }
        });

    }

    void checkQuestions() {

        if (questionCount == 2) {

            //allow no other options to be chosen
            afraid.setClickable(false);
            angry.setClickable(false);
            sad.setClickable(false);
            lonely.setClickable(false);
            worried.setClickable(false);
            embarrassed.setClickable(false);
            distracted.setClickable(false);
            other.setClickable(false);
            nervous.setClickable(false);

            Toast.makeText(this, "Two QUESTIONS", Toast.LENGTH_SHORT).show();

            Intent RateMood = new Intent(getApplicationContext(), RateTwoMoodActivity.class);
            RateMood.putExtra("questionnaireObj", questionnaire);
            RateMood.putExtra("chosenMoods", chosenMood);
            startActivity(RateMood);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
        }
        else if (questionCount == 3) {

            Toast.makeText(this, "THREE QUESTIONS", Toast.LENGTH_SHORT).show();

            Intent RateMood = new Intent(getApplicationContext(), RateOneMoodActivity.class);
            RateMood.putExtra("questionnaireObj", questionnaire);
            RateMood.putExtra("chosenMoods", chosenMood);
            startActivity(RateMood);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        }
    }

}
