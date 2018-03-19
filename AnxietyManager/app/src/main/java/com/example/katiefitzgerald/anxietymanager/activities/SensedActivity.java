package com.example.katiefitzgerald.anxietymanager.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.model.SensedAnxietyDao;
import com.example.katiefitzgerald.anxietymanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class SensedActivity extends AppCompatActivity {

    String user_id;
    String address;
    double longitude, latitude;
    LocationManager mLocationManager;

    DatabaseReference SensedAnxietyDB = FirebaseDatabase.getInstance().getReference("sensed_anxiety");

    final ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensed);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");

//
//        Location location = getLastKnownLocation();
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();

        populateEpisodeList();

    }

    public void populateEpisodeList() {

        DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query sensedAnxiety = sensedDB.child("sensed_anxiety").orderByChild("user_ID").equalTo(user_id);

        final ListView episodeList = findViewById(R.id.episodeListView);

        sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                 if (dataSnapshot.getValue() != null) {

                     for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                         Map<String, Object> map = (Map<String, Object>) dsp.getValue();
                         String timestamp = (String) map.get("timestamp");
                         String location = (String) map.get("location");

                         arrayList.add(timestamp + " at " + location);

                         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                         episodeList.setAdapter(arrayAdapter);
                     }

                 }
             }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    //use to put fake data into db
    public void inputSensorData() {

        //get current time
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("E, d MMM yyyy", Locale.getDefault());
        String currentTime = dateFormatDay.format(1521019800000L);

        //get location
        try {
            address = location();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView value = findViewById(R.id.value);
        value.setText(address);

        String sensed_id = SensedAnxietyDB.push().getKey();

        SensedAnxietyDao sensedEpisode = new SensedAnxietyDao(sensed_id, currentTime, address, user_id);

        SensedAnxietyDB.child(sensed_id).setValue(sensedEpisode);

    }

    private String location() throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        Address address = addresses.get(0);
        String streetName = address.getThoroughfare();

        if (streetName == null) {
            streetName = "Location unavailable";
        }

        return streetName;

    }


    // https://stackoverflow.com/questions/20438627/getlastknownlocation-returns-null
    private Location getLastKnownLocation() {

        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;

        for (String provider : providers) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION }, 10);
            }

            Location l = mLocationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

}







