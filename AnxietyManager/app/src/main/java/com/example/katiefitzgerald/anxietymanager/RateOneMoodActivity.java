package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Katie Fitzgerald on 12/03/2018.
 */

public class RateOneMoodActivity extends AppCompatActivity {

    ImageView nextQuestion;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_one);

        String[] chosenMoods = (String[]) getIntent().getSerializableExtra("chosenMoods");

        setupMoodImages(chosenMoods);

        seekBarSetup();

        nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Emotion = new Intent(getApplicationContext(), ReactActivity.class);
                startActivity(Emotion);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

            }
        });

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
    }

    private void setupMoodImages(String[] chosenMoods) {

        String mood_one = chosenMoods[0];

        TextView moodTwoOne = findViewById(R.id.mood_one);
        moodTwoOne.setText(mood_one);

        //ImageView moodOne = findViewById(R.id.moodOne);
        //ImageView moodTwo = findViewById(R.id.moodTwo);

    }

}
