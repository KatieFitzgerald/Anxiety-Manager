package com.example.katiefitzgerald.anxietymanager;

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

import java.sql.SQLException;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Katie Fitzgerald on 24/01/2018.
 */

public class ThoughtsActivity extends AppCompatActivity {

    EditText thoughtName;
    ImageButton nextQuestion;
    Button addThought;
    ImageButton previousQuestion;
    ListView thoughtList;
    ThoughtsAdapter cursorAdapter;
    String thoughtChosen;

    DatabaseManager db = new DatabaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoughts);

        final QuestionnaireDao questionnaireDao = (QuestionnaireDao) getIntent().getSerializableExtra("questionnaireObj");

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

                Toast.makeText(getApplicationContext(), thoughtChosen + " selected", Toast.LENGTH_LONG).show();

                startNextActivity();

            }
        });


//        previousQuestion = findViewById(R.id.previous);
//        previousQuestion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent WhatsUp = new Intent(getApplicationContext(), WhatsUpActivity.class);
//                startActivity(WhatsUp);
//                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
//            }
//        });

        addThought = findViewById(R.id.addThought);
        addThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String thoughtNameInput = thoughtName.getText().toString();

                //make sure there is something to add to list/database
                if(null!= thoughtNameInput && thoughtNameInput.length() > 0) {

                    try {
                        db.open();

                        db.insertThought(thoughtNameInput);

                        //refresh view
                        Cursor thoughts = db.selectThoughts();
                        cursorAdapter = new ThoughtsAdapter(ThoughtsActivity.this, thoughts);
                        cursorAdapter.notifyDataSetChanged();
                        thoughtList.setAdapter(cursorAdapter);

                        db.close();

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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent Physical = new Intent(getApplicationContext(), PhysicalActivity.class);
                startActivity(Physical);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
            }
        }, 1000);


    }


}
