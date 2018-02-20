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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SensedActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    int locationCount = 0;
    
    ArrayList<AnxietyEpisode> episode = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String user_id = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_main);

        addAnxiety();

        ListView episodeList = findViewById(R.id.episodeListView);
        episodeList.setAdapter(new SensedAnxietyAdapter(SensedActivity.this, R.layout.list_view_items, episode));

    }

    public String getAnxietyLocation() {

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

    private void addAnxiety() {
        Arduino arduino = new Arduino();
        episode  = arduino.getAnxiety(this);
        getAnxietyLocation();
    }
}






