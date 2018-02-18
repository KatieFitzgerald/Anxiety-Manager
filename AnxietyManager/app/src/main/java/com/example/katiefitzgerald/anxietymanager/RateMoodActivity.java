package com.example.katiefitzgerald.anxietymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Katie Fitzgerald on 30/01/2018.
 */

public class RateMoodActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        seekBarSetup();

    }

    private void seekBarSetup() {

        SeekBar seekBarOverall = findViewById(R.id.seekBarOverall);
        seekBarOverall.setProgress(1);
        seekBarOverall.incrementProgressBy(1);
        seekBarOverall.setMax(3);

        SeekBar seekBarMoodOne = findViewById(R.id.seekBarMoodOne);
        seekBarMoodOne.incrementProgressBy(1);
        seekBarMoodOne.setMax(10);

        final TextView seekBarValueMoodOne = findViewById(R.id.seekBarValMoodOne);
        //seekBarValue.setText(seekBar.getProgress());

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

        SeekBar seekBarMoodTwo = findViewById(R.id.seekBarMoodTwo);
        seekBarMoodTwo.setProgress(0);
        seekBarMoodTwo.incrementProgressBy(1);
        seekBarMoodTwo.setMax(10);

        final TextView seekBarValueMoodTwo = findViewById(R.id.seekBarValMoodTwo);
        //seekBarValue.setText(seekBar.getProgress());

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

}