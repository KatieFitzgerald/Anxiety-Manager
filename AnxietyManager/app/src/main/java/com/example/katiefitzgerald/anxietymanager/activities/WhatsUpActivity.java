package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.example.katiefitzgerald.anxietymanager.model.SensedAnxietyDao;
import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;
import com.example.katiefitzgerald.anxietymanager.model.QuestionnaireDao;
import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.adapters.WhatsUpAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Katie Fitzgerald on 15/01/2018.
 */

public class WhatsUpActivity extends AppCompatActivity {

    EditText worryName;
    Button addSituation;
    ListView worryList;
    String user;
    WhatsUpAdapter cursorAdapter;
    String sensed_id = null;
    String subjectChosen, currentTime;

    DatabaseManager db = new DatabaseManager(this);

    DatabaseReference SensedAnxietyDB = FirebaseDatabase.getInstance().getReference("sensed_anxiety");

    String questionnaire[] = new String[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("user_id");
        sensed_id = extras.getString("sensed_id");

        if (sensed_id == null){

            sensed_id = SensedAnxietyDB.push().getKey();

            //Create a sensed_anxiety instances
            String currentTime = String.valueOf(System.currentTimeMillis());

            SensedAnxietyDao sensedEpisode = new SensedAnxietyDao(sensed_id, currentTime, "none", user);

            SensedAnxietyDB.child(sensed_id).setValue(sensedEpisode);

            SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMM yyyy, H:m", Locale.getDefault());
            String cTime = dateFormatDay.format(System.currentTimeMillis());

            questionnaire[11] = cTime;
        }
        else {

            DatabaseReference sensedDB = FirebaseDatabase.getInstance().getReference();
            Query sensedAnxiety = sensedDB.child("sensed_anxiety").orderByChild("sensedID").equalTo(sensed_id);

            sensedAnxiety.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null) {

                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                            SensedAnxietyDao episode = dsp.getValue(SensedAnxietyDao.class);
                            currentTime = episode.getTimestamp();

                            SimpleDateFormat dateFormatDay = new SimpleDateFormat("d MMM yyyy, H:m", Locale.getDefault());
                            String cTime = dateFormatDay.format(Long.valueOf(currentTime));

                            questionnaire[11] = cTime;

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        questionnaire[0] = sensed_id;
        questionnaire[1] = user;

        worryName = findViewById(R.id.worryName);
        worryList = findViewById(R.id.worryList);

        try {
            db.open();

            Cursor subjects = db.selectSubjects();
            cursorAdapter = new WhatsUpAdapter(WhatsUpActivity.this, subjects);
            worryList.setAdapter(cursorAdapter);

            db.close();

        }
        catch (SQLException e) {
            Toast.makeText(WhatsUpActivity.this, "Error opening database", LENGTH_SHORT).show();
        }

        worryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {

                // Get the data associated with selected item
                Cursor returnedSubjectCursor = (Cursor) listView.getItemAtPosition(itemPosition);
                subjectChosen = returnedSubjectCursor.getString(1);

                questionnaire[2] = subjectChosen;

                Toast.makeText(getApplicationContext(), subjectChosen + " selected", Toast.LENGTH_SHORT).show();

                Intent Thoughts = new Intent(getApplicationContext(), ThoughtsActivity.class);
                Thoughts.putExtra("questionnaireObj", questionnaire);
                startActivity(Thoughts);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);


            }
        });

        addSituation = findViewById(R.id.addSituation);
        addSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get user input
                final String worryNameInput = worryName.getText().toString();

                //make sure there is something to add to list/database
                if(null!= worryNameInput && worryNameInput.length() > 0) {
                    try {

                        db.open();
                        db.insertSubject(worryNameInput);

                        //refresh view
                        Cursor searchResult = db.selectSubjects();
                        cursorAdapter = new WhatsUpAdapter(WhatsUpActivity.this, searchResult);
                        cursorAdapter.notifyDataSetChanged();
                        worryList.setAdapter(cursorAdapter);

                        questionnaire[2] = worryNameInput;

                        db.close();

                        Intent Thoughts = new Intent(getApplicationContext(), ThoughtsActivity.class);
                        Thoughts.putExtra("questionnaireObj", questionnaire);
                        startActivity(Thoughts);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

                    }
                    catch (SQLException e) {
                        Toast.makeText(WhatsUpActivity.this, "Error inserting into database", LENGTH_SHORT).show();
                    }

                }
                else {
                    final TextView warning = findViewById(R.id.warning);
                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter a situation");

                    worryName.addTextChangedListener(new TextWatcher()
                    {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        public void onTextChanged(CharSequence s, int start, int before, int count){
                            warning.setText("");
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                }
            }
        });

    }

}



