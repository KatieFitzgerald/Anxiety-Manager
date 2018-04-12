package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 11/04/2018.
 */

public class ChoosePatientActivity extends AppCompatActivity {

    final ArrayList<String> arrayList = new ArrayList<>();
    ListView userList;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor);

        userList = findViewById(R.id.userList);
        logout = findViewById(R.id.logoutBtn);

        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        logout.setBackgroundResource(R.drawable.pressed);
                        logout.setTextColor(Color.GRAY);

                        Intent Logout = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(Logout);

                    case MotionEvent.ACTION_UP:

                        logout.setBackgroundResource(R.drawable.shape);
                        logout.setTextColor(Color.WHITE);

                        return true;

                    default:
                        return false;
                }
            }
        });

        chooseUser();
    }

    @Override
    public void onBackPressed() {
    }


    private void chooseUser(){

        //populate list with registered users
        DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
        Query sensedAnxiety = sensedDB.child("user_profile").orderByChild("user_ID");

        sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {

                    for (DataSnapshot userData : dataSnapshot.getChildren()) {

                        User user = userData.getValue(User.class);

                        if(!user.getCounsellor()){

                            arrayList.add(user.getName());
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                        userList.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId) {

            // Get the data associated with selected item
            String username = (String) listView.getItemAtPosition(itemPosition);

            Toast.makeText(getApplicationContext(), "Welcome to " + username + "\'s profile!", Toast.LENGTH_SHORT).show();

            DatabaseReference userDB = FirebaseDatabase.getInstance().getReference();
            Query chosenUser = userDB.child("user_profile").orderByChild("name").equalTo(username);

            chosenUser.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null) {

                        for (DataSnapshot userData : dataSnapshot.getChildren()) {

                            //get ID of chosen user
                            User user = userData.getValue(User.class);
                            String user_id = user.getId();

                            Intent chosen = new Intent(getApplicationContext(), CounsellorHomeActivity.class);
                            chosen.putExtra("user_id", user_id);
                            startActivity(chosen);

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            }
        });
    }

}
