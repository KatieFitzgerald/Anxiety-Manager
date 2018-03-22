package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;

/**
 * Created by Katie Fitzgerald on 30/01/2018.
 */

public class RateTwoMoodActivity extends AppCompatActivity {

    ImageView nextQuestion;

    String questionnaire[] = new String[11];
    String[] chosenMoods = new String[2];

    SeekBar seekBarMoodOne;
    SeekBar seekBarMoodTwo;
    SeekBar seekBarOverall;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");
        chosenMoods = extras.getStringArray("chosenMoods");

        seekBarMoodOne = findViewById(R.id.seekbarMoodOne);
        seekBarMoodTwo = findViewById(R.id.seekbarMoodTwo);
        seekBarOverall = findViewById(R.id.seekBarOverall);

        setupMoodImages(chosenMoods);

        seekBarSetup();

        nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reaction = new Intent(getApplicationContext(), ReactActivity.class);
                Reaction.putExtra("questionnaireObj", questionnaire);
                startActivity(Reaction);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

            }
        });

    }

    private void seekBarSetup() {

        seekBarMoodOne.incrementProgressBy(1);
        seekBarMoodOne.setMax(10);

        final TextView seekBarValueMoodOne = findViewById(R.id.moodOneValue);

        seekBarMoodOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueMoodOne.setText(String.valueOf(progress));
                questionnaire[7] = String.valueOf(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarMoodTwo.incrementProgressBy(1);
        seekBarMoodTwo.setMax(10);

        final TextView seekBarValueMoodTwo = findViewById(R.id.moodTwoValue);

        seekBarMoodTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueMoodTwo.setText(String.valueOf(progress));
                questionnaire[8] = String.valueOf(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarOverall.incrementProgressBy(1);
        seekBarOverall.setMax(5);

        seekBarOverall.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                questionnaire[9] = String.valueOf(progress);

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

    }

}