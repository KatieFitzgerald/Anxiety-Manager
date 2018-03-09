package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Katie Fitzgerald on 30/01/2018.
 */

public class RateMoodActivity extends AppCompatActivity {

    ImageButton nextQuestion;
    ImageButton previousQuestion;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        String[] chosenMoods = (String[]) getIntent().getSerializableExtra("chosenMoods");

        setupMoodImages(chosenMoods);

        seekBarSetup();

        /*nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Emotion = new Intent(getApplicationContext(), ReactActivity.class);
                startActivity(Emotion);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

            }
        });*/

        /*previousQuestion = findViewById(R.id.previous);
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Thoughts = new Intent(getApplicationContext(), MoodActivity.class);
                startActivity(Thoughts);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });*/

    }

    private void seekBarSetup() {

        SeekBar seekBarOverall = findViewById(R.id.seekBarOverall);
        seekBarOverall.setProgress(1);
        seekBarOverall.incrementProgressBy(1);
        seekBarOverall.setMax(3);

        SeekBar seekBarMoodOne = findViewById(R.id.seekbarMoodOne);
        seekBarMoodOne.incrementProgressBy(1);
        seekBarMoodOne.setMax(10);

        final TextView seekBarValueMoodOne = findViewById(R.id.moodOneValue);

        seekBarMoodOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueMoodOne.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBarMoodTwo = findViewById(R.id.seekbarMoodTwo);
        seekBarMoodTwo.setProgress(0);
        seekBarMoodTwo.incrementProgressBy(1);
        seekBarMoodTwo.setMax(10);

        final TextView seekBarValueMoodTwo = findViewById(R.id.moodTwoValue);

        seekBarMoodTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueMoodTwo.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void setupMoodImages(String[] chosenMoods) {

        String mood_one = chosenMoods[0];
        String mood_two = chosenMoods[1];

        TextView moodTwoOne = findViewById(R.id.mood_one);
        moodTwoOne.setText(mood_one);

        TextView moodTwoName = findViewById(R.id.mood_two);
        moodTwoName.setText(mood_two);

        //ImageView moodOne = findViewById(R.id.moodOne);
        //ImageView moodTwo = findViewById(R.id.moodTwo);

        Toast.makeText(this, "Mood one: " + mood_one + " Mood two: " + mood_two, Toast.LENGTH_SHORT).show();

    }

}