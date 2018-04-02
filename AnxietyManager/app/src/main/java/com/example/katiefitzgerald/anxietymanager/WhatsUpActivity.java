package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Katie Fitzgerald on 15/01/2018.
 */

public class WhatsUpActivity extends AppCompatActivity {

    ArrayAdapter<String> worryAdapter;
    ArrayList<String> worryArrayList = new ArrayList<String>();
    EditText worryName;
    ImageButton nextQuestion;
    Button addSituation;
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

        final Toast toast = Toast.makeText(this, "Situation has been added", Toast.LENGTH_SHORT);

        addSituation = findViewById(R.id.addSituation);
        addSituation.setOnClickListener(new View.OnClickListener() {
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
                    toast.show();

                    nextQuestion = findViewById(R.id.next);
                    nextQuestion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Thoughts = new Intent(getApplicationContext(), ThoughtsActivity.class);
                            startActivity(Thoughts);
                            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);
                        }
                    });
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


