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
 * Created by Katie Fitzgerald on 24/01/2018.
 */

public class ThoughtsActivity extends AppCompatActivity {

    ArrayAdapter<String> thoughtAdapter;
    ArrayList<String> thoughtArrayList = new ArrayList<String>();
    EditText thoughtName;
    ImageButton nextQuestion;
    Button addThought;
    ImageButton previousQuestion;
    ListView thoughtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoughts);

        QuestionnaireDao questionnaireDao = (QuestionnaireDao) getIntent().getSerializableExtra("questionnaireObj");

        Toast.makeText(this, questionnaireDao.getSubject_ID(), Toast.LENGTH_SHORT).show();

        //get user input
        thoughtName = findViewById(R.id.thoughtName);

        thoughtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thoughtArrayList);
        thoughtList = findViewById(R.id.thoughtList);
        thoughtList.setAdapter(thoughtAdapter);

        final Toast toast = Toast.makeText(this, "Thought has been added", Toast.LENGTH_SHORT);

        previousQuestion = findViewById(R.id.previous);
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent WhatsUp = new Intent(getApplicationContext(), WhatsUpActivity.class);
                startActivity(WhatsUp);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
            }
        });

        addThought = findViewById(R.id.addThought);
        addThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String thoughtNameInput = thoughtName.getText().toString();

                //make sure there is something to add to list/database
                if(null!= thoughtNameInput && thoughtNameInput.length() > 0) {

                    /**
                     *
                     *
                     * add worry to db here
                     **/

                    thoughtArrayList.add(thoughtNameInput);
                    thoughtAdapter.notifyDataSetChanged();
                    toast.show();

                    nextQuestion = findViewById(R.id.next);
                    nextQuestion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        Intent Physical = new Intent(getApplicationContext(), PhysicalActivity.class);
                        startActivity(Physical);
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

                        }
                    });
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

}
