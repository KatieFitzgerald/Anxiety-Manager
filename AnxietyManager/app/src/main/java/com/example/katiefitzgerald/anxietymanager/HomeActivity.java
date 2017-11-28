package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button sensed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sensed = (Button) findViewById(R.id.sensedAnxiety);

        sensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensedActivity = new Intent(getApplicationContext(), SensedActivity.class);
                startActivity(sensedActivity);
            }
        });
    }
}
