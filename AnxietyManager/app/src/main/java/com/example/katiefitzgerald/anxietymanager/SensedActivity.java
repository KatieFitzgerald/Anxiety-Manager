package com.example.katiefitzgerald.anxietymanager;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SensedActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;

    String locationText = " Kevin Street";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<AnxietyEpisode> anxietyEpisode = new ArrayList<>();
        getSensedAnxiety(anxietyEpisode);

        ListView episodeList = (ListView) findViewById(R.id.episodeListView);
        episodeList.setAdapter(new SensedAnxietyAdapter(SensedActivity.this, R.layout.list_view_items, anxietyEpisode));
    }

    //Based on tutorial https://www.youtube.com/watch?v=i-TqNzUryn8

    private void getSensedAnxiety(ArrayList<AnxietyEpisode> anxietyEpisode) {

        InputStream is = getResources().openRawResource(R.raw.sensed);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        https://www.youtube.com/watch?v=QNb_3QKSmMk
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationText = " " + location.getLatitude() + " " + location.getLongitude();
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

        try {
            while( (line = reader.readLine()) != null) {
                //Split by ","
                String[] tokens = line.split(",");

                //Read the data
                AnxietyEpisode episode = new AnxietyEpisode();
                episode.setDate(tokens[0]);
                episode.setTime(tokens[1]);
                episode.setLocation(locationText);

                anxietyEpisode.add(episode);
            }
        } catch (IOException e) {
            Log.wtf("My Activity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }
}





