package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;

/**
 * Created by Katie Fitzgerald on 12/03/2018.
 */

public class RateOneMoodActivity extends AppCompatActivity {

    ImageView nextQuestion;

    String questionnaire[] = new String[12];
    String[] chosenMoods = new String[2];

    SeekBar seekBarMoodOne;
    SeekBar seekBarOverall;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_one);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");
        chosenMoods = extras.getStringArray("chosenMoods");

        seekBarMoodOne = findViewById(R.id.seekbarMoodOne);
        seekBarOverall = findViewById(R.id.seekBarOverall);

        setupMoodImages(chosenMoods);

        seekBarSetup();

        questionnaire[7] = String.valueOf(seekBarMoodOne.getProgress());
        questionnaire[8] = "Custom emotion chosen";
        questionnaire[9] = String.valueOf(seekBarOverall.getProgress());

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

        seekBarOverall.incrementProgressBy(1);
        seekBarOverall.setMax(3);

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
    }

}
