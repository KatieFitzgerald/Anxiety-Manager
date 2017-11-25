package com.example.katiefitzgerald.anxietymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<AnxietyEpisode> anxietyEpisode = new ArrayList<>();
        getSensedAnxiety(anxietyEpisode);

        ListView episodeList = (ListView) findViewById(R.id.episodeListView);
        episodeList.setAdapter(new SensedAnxietyAdapter(MainActivity.this, R.layout.list_view_items, anxietyEpisode));
    }

    //Based on tutorial https://www.youtube.com/watch?v=i-TqNzUryn8

    private void getSensedAnxiety(ArrayList<AnxietyEpisode> anxietyEpisode){

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
            }
        } catch (IOException e) {
            Log.wtf("My Activity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }
}


