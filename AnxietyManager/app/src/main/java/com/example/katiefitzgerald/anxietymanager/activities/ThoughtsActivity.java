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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;
import com.example.katiefitzgerald.anxietymanager.model.QuestionnaireDao;
import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.adapters.ThoughtsAdapter;

import java.sql.SQLException;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Katie Fitzgerald on 24/01/2018.
 */

public class ThoughtsActivity extends AppCompatActivity {

    EditText thoughtName;
    Button addThought;
    ListView thoughtList;
    ThoughtsAdapter cursorAdapter;
    String thoughtChosen, user;

    String questionnaire[] = new String[10];

    DatabaseManager db = new DatabaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoughts);

        Bundle extras = getIntent().getExtras();
        questionnaire = extras.getStringArray("questionnaireObj");

        //get user input
        thoughtName = findViewById(R.id.thoughtName);
        thoughtList = findViewById(R.id.thoughtList);

        try {
            db.open();

            Cursor thoughts = db.selectThoughts();
            cursorAdapter = new ThoughtsAdapter(ThoughtsActivity.this, thoughts);
            thoughtList.setAdapter(cursorAdapter);

            db.close();

        }
        catch (SQLException e) {
            Toast.makeText(ThoughtsActivity.this, "Error opening database", LENGTH_SHORT).show();
        }

        thoughtList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                // Get the data associated with selected item

                Cursor returnedThoughtCursor = (Cursor) listView.getItemAtPosition(itemPosition);
                thoughtChosen = returnedThoughtCursor.getString(1);

                questionnaire[2] = thoughtChosen;

                Toast.makeText(getApplicationContext(), thoughtChosen + " selected", Toast.LENGTH_LONG).show();

                startNextActivity();

            }
        });

        addThought = findViewById(R.id.addThought);
        addThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String thoughtNameInput = thoughtName.getText().toString();

                //make sure there is something to add to list/database
                if(null != thoughtNameInput && thoughtNameInput.length() > 0) {

                    try {
                        db.open();

                        db.insertThought(thoughtNameInput);

                        //refresh view
                        Cursor thoughts = db.selectThoughts();
                        cursorAdapter = new ThoughtsAdapter(ThoughtsActivity.this, thoughts);
                        cursorAdapter.notifyDataSetChanged();
                        thoughtList.setAdapter(cursorAdapter);

                        db.close();

                        questionnaire[2] = thoughtNameInput;

                        Toast.makeText(getApplicationContext(), thoughtNameInput + " added", Toast.LENGTH_LONG).show();

                        startNextActivity();

                    }
                    catch (SQLException e) {
                        Toast.makeText(ThoughtsActivity.this, "Error inserting into database", LENGTH_SHORT).show();
                    }

                }
                else {
                    final TextView warning = findViewById(R.id.warning);
                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter a thought");

                    thoughtName.addTextChangedListener(new TextWatcher()
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

    public void startNextActivity(){

        Intent Physical = new Intent(getApplicationContext(), PhysicalActivity.class);
        Physical.putExtra("questionnaireObj", questionnaire);
        startActivity(Physical);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

    }


}
