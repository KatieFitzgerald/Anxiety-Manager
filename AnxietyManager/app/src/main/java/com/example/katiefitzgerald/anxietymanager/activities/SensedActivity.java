package com.example.katiefitzgerald.anxietymanager.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.model.Questionnaire;
import com.example.katiefitzgerald.anxietymanager.model.SensedAnxiety;
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

    String user_id, sensedID;
    String address;
    double longitude, latitude;
    LocationManager mLocationManager;
    ListView episodeList;
    Button questionnaireStart;
    View questionnaireDetails;

    DatabaseReference SensedAnxietyDB = FirebaseDatabase.getInstance().getReference("sensed_anxiety");

    final ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensed);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");

        episodeList = findViewById(R.id.episodeListView);

        questionnaireStart = findViewById(R.id.fillOutQuestionnaire);
        questionnaireStart.setVisibility(View.INVISIBLE);

        questionnaireDetails = findViewById(R.id.questionnaireDetails);
        questionnaireDetails.setVisibility(View.INVISIBLE);

//        Location location = getLastKnownLocation();
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();

        populateEpisodeList();
        listCheck();

    }

    public void populateEpisodeList() {

        DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query sensedAnxiety = sensedDB.child("sensed_anxiety").orderByChild("user_ID").equalTo(user_id);

        sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                 if (dataSnapshot.getValue() != null) {

                     for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                         Map<String, Object> map = (Map<String, Object>) dsp.getValue();
                         String timestampLong = (String) map.get("timestamp");
                         String location = (String) map.get("location");

                         //get current time
                         SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMM yyyy, H:m", Locale.getDefault());
                         String currentTime = dateFormatDay.format(Long.valueOf(timestampLong));

                         if(location.equals("none")){
                             arrayList.add(currentTime + " from questionnaire");
                         }
                         else {
                             arrayList.add(currentTime + " at " + location);
                         }

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
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMM yyyy, H:m", Locale.getDefault());
        String currentTime = dateFormatDay.format(1521019800000L);

        //get location
        try {
            address = location();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sensed_id = SensedAnxietyDB.push().getKey();

        SensedAnxiety sensedEpisode = new SensedAnxiety(sensed_id, currentTime, address, user_id);

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

    //Code snippet https://stackoverflow.com/questions/20438627/getlastknownlocation-returns-null
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

    private void listCheck() {

        episodeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId) {

                // Get the data associated with selected item
                String episode = (String) listView.getItemAtPosition(itemPosition);

                String[] split = episode.split("\\s+");

                String timestamp = split[0] + " "  + split[1] + " "  + split[2] + " "  + split[3];
                String location = new String();

                for(int i = 5; i < split.length; i++){
                    location += split[i] + " ";
                }

                if (location.equals("questionnaire ")){

                    questionnaireAssoc("none", timestamp);

                }
                else {
                    questionnaireAssoc(location.trim(), timestamp);
                }

            }

        });

    }

    private void questionnaireAssoc(String location, final String timestamp) {

        //get user from user_id, get location of anxiety attack, then user sensed_id from attack and join the questionnaire with that sensed_id
        final DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query userRef = sensedDB.child("sensed_anxiety").orderByChild("user_ID").equalTo(user_id);
        final Query locationRef = sensedDB.child("sensed_anxiety").orderByChild("location").equalTo(location);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null){

                    locationRef.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null) {

                                for (DataSnapshot episodeData : dataSnapshot.getChildren()) {

                                    SensedAnxiety episode = episodeData.getValue(SensedAnxiety.class);
                                    sensedID = episode.getSensedID();
                                    final String loc = episode.getLocation();

                                    final Query questionnaireSensedRef = sensedDB.child("questionnaire").orderByChild("sensed_ID").equalTo(sensedID);
                                    final Query questionnaireTimestampRef = sensedDB.child("questionnaire").orderByChild("timestamp").equalTo(timestamp);

                                    questionnaireSensedRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.getValue() != null) {

                                                questionnaireTimestampRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        //if there is a questionnaire to display
                                                        if (dataSnapshot.getValue() != null) {

                                                            for (DataSnapshot questionnaireData : dataSnapshot.getChildren()) {

                                                                Questionnaire questionnaire = questionnaireData.getValue(Questionnaire.class);
                                                                String subject = questionnaire.getSubject();
                                                                String reaction = questionnaire.getReaction();
                                                                String thought = questionnaire.getThought();

                                                                questionnaireStart.setVisibility(View.INVISIBLE);
                                                                questionnaireDetails.setVisibility(View.VISIBLE);

                                                                TextView location = questionnaireDetails.findViewById(R.id.location);
                                                                if(loc.equals("none")) {
                                                                    location.setText("- Unknown");

                                                                }
                                                                else {
                                                                    location.setText("- " + loc);
                                                                }
                                                                TextView subjectText = questionnaireDetails.findViewById(R.id.subject);
                                                                subjectText.setText(subject);
                                                                TextView reactionText = questionnaireDetails.findViewById(R.id.reaction);
                                                                reactionText.setText(reaction);
                                                                TextView thoughtText = questionnaireDetails.findViewById(R.id.thought);
                                                                thoughtText.setText(thought);
                                                            }

                                                        }
                                                        else {

                                                            questionnaireDetails.setVisibility(View.INVISIBLE);
                                                            questionnaireStart.setVisibility(View.VISIBLE);

                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}