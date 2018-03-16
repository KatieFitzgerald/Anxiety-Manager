package com.example.katiefitzgerald.anxietymanager.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.model.AnxietyEpisode;
import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.adapters.SensedAnxietyAdapter;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SensedActivity extends AppCompatActivity {

    int read = 1;

    TextView value;
    double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensed);

        Bundle extras = getIntent().getExtras();
        longitude = extras.getDouble("Longitude");
        latitude = extras.getDouble("Latitude");

        value = findViewById(R.id.value);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2 seconds testing
                while (read > 0) {
                    sensorData();
                    read -= 1;
                }
            }
        }, 2000);

    }

    private void sensorData() {

        //get current time
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("E, d MMM yyyy", Locale.getDefault());
        String currentTime = dateFormatDay.format(System.currentTimeMillis());

        String address = null;

        //get location
        try {
            address = getLocation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        value.setText("Anxiety Sensed at " + currentTime + " at " + address);

        //update list view

    }

    private String getLocation() throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        Address address = addresses.get(0);
        String streetName = address.getThoroughfare();

        return streetName;

    }

}







