package com.example.katiefitzgerald.anxietymanager;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView disgust;
    ImageView embarrassed;
    ImageView distracted;
    ImageView nervous;
    ImageView other;

    MoodDialogAdapter cursorAdapter;

    DatabaseManager db = new DatabaseManager(this);

    String[] chosenMood = new String[2];

    ImageButton nextQuestion;
    ImageButton previousQuestion;

    TextView warning;

    int questionCount = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        afraid = findViewById(R.id.afraid);
        angry = findViewById(R.id.angry);
        sad = findViewById(R.id.sad);
        lonely = findViewById(R.id.lonely);
        disgust = findViewById(R.id.worried);
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
                                questionCount += 1;
                                checkQuestions();
                            }
                            else {
                                chosenMood[0] = moodChosen;
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
                                    questionCount += 1;
                                    checkQuestions();
                                }
                                else {
                                    chosenMood[0] = otherEmotion;
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

        if (questionCount == 2) {
            //allow no other options to be chosen
            afraid.setClickable(false);
            angry.setClickable(false);
            sad.setClickable(false);
            lonely.setClickable(false);
            disgust.setClickable(false);
            embarrassed.setClickable(false);
            distracted.setClickable(false);
            other.setClickable(false);
            nervous.setClickable(false);

            Toast.makeText(this, "Two QUESTIONS", Toast.LENGTH_SHORT).show();

            Intent RateMood = new Intent(getApplicationContext(), RateTwoMoodActivity.class);
            RateMood.putExtra("chosenMoods", chosenMood);
            startActivity(RateMood);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
        }
        else if (questionCount == 3) {

            Toast.makeText(this, "THREE QUESTIONS", Toast.LENGTH_SHORT).show();

            Intent RateMood = new Intent(getApplicationContext(), RateOneMoodActivity.class);
            RateMood.putExtra("chosenMoods", chosenMood);
            startActivity(RateMood);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        }
    }

}
