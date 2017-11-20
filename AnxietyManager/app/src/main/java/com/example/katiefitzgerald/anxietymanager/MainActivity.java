package com.example.katiefitzgerald.anxietymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSensedAnxiety();
    }

    //Based on tutorial https://www.youtube.com/watch?v=i-TqNzUryn8
    private List<AnxietyEpisode> anxietyEpisode = new ArrayList<>();
    private void getSensedAnxiety(){

        InputStream is = getResources().openRawResource(R.raw.sensed);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        try {
            while( (line = reader.readLine()) != null) {
                //Split by ","
                String[] tokens = line.split(",");

                //Read the data
                AnxietyEpisode episode = new AnxietyEpisode();
                episode.setDate(tokens[0]);
                episode.setTime(tokens[1]);

                anxietyEpisode.add(episode);

                Log.d("MyActivity", "just created" + episode);
            }
        } catch (IOException e) {
            Log.wtf("My Activity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }
}


