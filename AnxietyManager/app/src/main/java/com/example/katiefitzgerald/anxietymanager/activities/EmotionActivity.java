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
import com.example.katiefitzgerald.anxietymanager.adapters.EmotionDialogAdapter;
import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;

import java.sql.SQLException;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Katie Fitzgerald on 26/01/2018.
 */

public class EmotionActivity extends AppCompatActivity {

    ImageView fear;
    ImageView angry;
    ImageView sad;
    ImageView lonely;
    ImageView worried;
    ImageView embarrassed;
    ImageView distracted;
    ImageView nervous;
    ImageView other;

    EmotionDialogAdapter cursorAdapter;

    DatabaseManager db = new DatabaseManager(this);

    String[] chosenEmotion = new String[2];
    String questionnaire[] = new String[12];

    TextView warning;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");

        fear = findViewById(R.id.afraid);
        angry = findViewById(R.id.angry);
        sad = findViewById(R.id.sad);
        lonely = findViewById(R.id.lonely);
        worried = findViewById(R.id.worried);
        embarrassed = findViewById(R.id.embarrassed);
        distracted = findViewById(R.id.distracted);
        nervous = findViewById(R.id.nervous);
        other = findViewById(R.id.other);

        fear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fear.setImageResource(R.drawable.chosen);
                if (questionCount == 0) {
                    chosenEmotion[0] = "Fear";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Fear";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Angry";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Angry";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Sad";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Sad";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Lonely";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Lonely";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Worried";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Worried";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Embarrassed";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Embarrassed";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Distracted";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Distracted";
                    questionnaire[6] = chosenEmotion[1];
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
                    chosenEmotion[0] = "Nervous";
                    questionnaire[5] = chosenEmotion[0];
                }
                else {
                    chosenEmotion[1] = "Nervous";
                    questionnaire[6] = chosenEmotion[1];

                }
                questionCount += 1;
                checkQuestions();
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder otherDialog = new AlertDialog.Builder(EmotionActivity.this);
                final View dialogView = getLayoutInflater().inflate(R.layout.mood_dialog, null);
                final EditText addOther = dialogView.findViewById(R.id.otherEmotion);
                final ListView emotionList = dialogView.findViewById(R.id.emotionList);
                Button add = dialogView.findViewById(R.id.add);

                try {
                    db.open();

                    Cursor moods = db.selectMood();
                    cursorAdapter = new EmotionDialogAdapter(EmotionActivity.this, moods);
                    emotionList.setAdapter(cursorAdapter);

                    db.close();

                    emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId) {
                            // Get the data associated with selected item

                            Cursor returnedMoodCursor = (Cursor) listView.getItemAtPosition(itemPosition);
                            String moodChosen = returnedMoodCursor.getString(1);

                            if (questionCount == 1) {
                                chosenEmotion[1] = moodChosen;
                                questionnaire[5] = chosenEmotion[1];
                                questionCount += 1;
                                checkQuestions();
                            }
                            else {
                                chosenEmotion[0] = moodChosen;
                                questionnaire[5] = chosenEmotion[0];
                                questionnaire[6] = "Custom emotion chosen";
                                questionCount = 3;
                                checkQuestions();
                            }
                        }

                    });

                }
                catch (SQLException e) {
                    Toast.makeText(EmotionActivity.this, "Error opening database", LENGTH_SHORT).show();
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
                                cursorAdapter = new EmotionDialogAdapter(EmotionActivity.this, moods);
                                cursorAdapter.notifyDataSetChanged();
                                emotionList.setAdapter(cursorAdapter);

                                db.close();

                                if (questionCount == 1) {
                                    chosenEmotion[1] = otherEmotion;
                                    questionnaire[5] = chosenEmotion[1];
                                    questionCount += 1;
                                    checkQuestions();
                                }
                                else {
                                    chosenEmotion[0] = otherEmotion;
                                    questionnaire[5] = chosenEmotion[0];
                                    questionnaire[6] = "Custom emotion chosen";
                                    questionCount = 3;
                                    checkQuestions();
                                }

                            }
                            catch (SQLException e) {
                                Toast.makeText(EmotionActivity.this, "Error inserting into database", LENGTH_SHORT).show();
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
            fear.setClickable(false);
            angry.setClickable(false);
            sad.setClickable(false);
            lonely.setClickable(false);
            worried.setClickable(false);
            embarrassed.setClickable(false);
            distracted.setClickable(false);
            other.setClickable(false);
            nervous.setClickable(false);

            Intent RateMood = new Intent(getApplicationContext(), RateTwoMoodActivity.class);
            RateMood.putExtra("questionnaireObj", questionnaire);
            RateMood.putExtra("chosenMoods", chosenEmotion);
            startActivity(RateMood);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
        }
        else if (questionCount == 3) {

            Intent RateMood = new Intent(getApplicationContext(), RateOneMoodActivity.class);
            RateMood.putExtra("questionnaireObj", questionnaire);
            RateMood.putExtra("chosenMoods", chosenEmotion);
            startActivity(RateMood);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        }
    }

}
