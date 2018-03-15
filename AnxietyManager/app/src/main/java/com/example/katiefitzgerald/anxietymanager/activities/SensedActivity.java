package com.example.katiefitzgerald.anxietymanager.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.katiefitzgerald.anxietymanager.model.AnxietyEpisode;
import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.adapters.SensedAnxietyAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

public class SensedActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    int locationCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String user_id = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_main);

        ArrayList<AnxietyEpisode> anxietyEpisode = new ArrayList<>();
        addAnxiety(anxietyEpisode, user_id);

        ListView episodeList = findViewById(R.id.episodeListView);
        episodeList.setAdapter(new SensedAnxietyAdapter(SensedActivity.this, R.layout.list_view_sensed_anxiety, anxietyEpisode));

    }

    //Based on tutorial https://www.youtube.com/watch?v=i-TqNzUryn8

    private BufferedReader getSensedAnxiety() {

        //read in data
        InputStream is = getResources().openRawResource(R.raw.sensed);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        return reader;

    }

    private String getAnxietyLocation() {

        final String[] locationText = new String[1];

        //Based on tutorial https://www.youtube.com/watch?v=QNb_3QKSmMk
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationText[0] = " " + location.getLatitude() + " " + location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (locationCount != 1) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {


                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            0);
                }
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
        }

        return Arrays.toString(locationText);
    }

    private void addAnxiety(ArrayList<AnxietyEpisode> anxietyEpisode, String userId) {

        String line = "";
        BufferedReader reader = getSensedAnxiety();

        try {

            while((line = reader.readLine()) != null) {
                //Split by ","
                String[] tokens = line.split(",");

                //Read the data
                AnxietyEpisode episode = new AnxietyEpisode();
                episode.setDate(tokens[0]);
                episode.setTime(tokens[1]);
                episode.setLocation(getAnxietyLocation());
                episode.setUser_id(userId);

                anxietyEpisode.add(episode);
                locationCount = 1;

            }
        } catch (IOException e) {
            Log.wtf("My Activity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }
}






