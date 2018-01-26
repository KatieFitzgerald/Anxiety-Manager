package com.example.katiefitzgerald.anxietymanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 15/01/2018.
 */

public class WhatsUpActivity extends AppCompatActivity {

    ArrayAdapter<String> worryAdapter;
    ArrayList<String> worryArrayList = new ArrayList<String>();
    EditText worryName;
    ImageButton nextQuestion;
    ListView worryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsup);

        //get user input
        worryName = findViewById(R.id.worryName);

        worryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, worryArrayList);
        worryList = findViewById(R.id.worryList);
        worryList.setAdapter(worryAdapter);

        //put user input into an array
        nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String worryNameInput = worryName.getText().toString();

                //make sure there is something to add to list/database
                if(null!= worryNameInput && worryNameInput.length() > 0) {

                    /**
                     *
                     *
                     * add worry to db here
                     **/

                    worryArrayList.add(worryNameInput);
                    worryAdapter.notifyDataSetChanged();
                }

                Intent Thoughts = new Intent(getApplicationContext(), ThoughtsActivity.class);
                startActivity(Thoughts);

            }
        });

    }

}



