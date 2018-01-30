package com.example.katiefitzgerald.anxietymanager;

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
 * Created by Katie Fitzgerald on 24/01/2018.
 */

public class ThoughtsActivity extends AppCompatActivity {

    ArrayAdapter<String> thoughtAdapter;
    ArrayList<String> thoughtArrayList = new ArrayList<String>();
    EditText thoughtName;
    ImageButton nextQuestion;
    ImageButton previousQuestion;
    ListView thoughtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoughts);

        //get user input
        thoughtName = findViewById(R.id.thoughtName);

        thoughtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thoughtArrayList);
        thoughtList = findViewById(R.id.thoughtList);
        thoughtList.setAdapter(thoughtAdapter);

        //put user input into an array
        nextQuestion = findViewById(R.id.next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String thoughtNameInput = thoughtName.getText().toString();

                //make sure there is something to add to list/database
                if (null != thoughtNameInput && thoughtNameInput.length() > 0) {

                    /**
                     *
                     *
                     * add thought to db here
                     **/

                    thoughtArrayList.add(thoughtNameInput);
                    thoughtAdapter.notifyDataSetChanged();
                }

                Intent Physical = new Intent(getApplicationContext(), PhysicalActivity.class);
                startActivity(Physical);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

            }
        });

        previousQuestion = findViewById(R.id.previous);
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent WhatsUp = new Intent(getApplicationContext(), WhatsUpActivity.class);
                startActivity(WhatsUp);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });
    }

}
