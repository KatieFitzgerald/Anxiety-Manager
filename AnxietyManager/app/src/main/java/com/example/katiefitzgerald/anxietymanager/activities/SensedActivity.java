package com.example.katiefitzgerald.anxietymanager.activities;

import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.model.SensedAnxietyDao;
import com.example.katiefitzgerald.anxietymanager.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SensedActivity extends AppCompatActivity {

    int read = 1;

    TextView value;
    double longitude, latitude;
    String user_id;
    String address;

    DatabaseReference SensedAnxietyDB = FirebaseDatabase.getInstance().getReference("sensed_anxiety");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensed);

        Bundle extras = getIntent().getExtras();
        longitude = extras.getDouble("Longitude");
        latitude = extras.getDouble("Latitude");
        user_id = extras.getString("user_id");

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

        //get location
        try {
            address = location();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sensed_id = SensedAnxietyDB.push().getKey();

        SensedAnxietyDao sensedEpisode = new SensedAnxietyDao(sensed_id, currentTime, address, user_id);

        SensedAnxietyDB.child(sensed_id).setValue(sensedEpisode);

        //update list view

    }

    private String location() throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        Address address = addresses.get(0);
        String streetName = address.getThoroughfare();

        if (streetName == null){
            streetName =  "Location not found";
        }

        return streetName;

    }

}







