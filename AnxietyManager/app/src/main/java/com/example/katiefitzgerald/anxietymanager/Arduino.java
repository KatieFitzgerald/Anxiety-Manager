package com.example.katiefitzgerald.anxietymanager;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 19/02/2018.
 */

public class Arduino {

    ArrayList<AnxietyEpisode> anxietyEpisode = new ArrayList<>();

    public ArrayList<AnxietyEpisode> getAnxiety(SensedActivity sensedActivity) {

        String line = "";

        //Based on tutorial https://www.youtube.com/watch?v=i-TqNzUryn8

        //read in data
        InputStream is = sensedActivity.getResources().openRawResource(R.raw.sensed);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        try {

            while((line = reader.readLine()) != null) {

                //Split by ","
                String[] tokens = line.split(",");

                //Read the data
                AnxietyEpisode episode = new AnxietyEpisode();
                episode.setDate(tokens[0]);
                episode.setTime(tokens[1]);

                anxietyEpisode.add(episode);
            }
        }
        catch (IOException e) {
            Log.wtf("Arduino", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

        return anxietyEpisode;

    }

}
