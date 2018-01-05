package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    String user_id;
    Button sensed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_id = getIntent().getStringExtra("userId");

        setContentView(R.layout.activity_home);

        sensed = findViewById(R.id.sensedAnxiety);

        sensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensedActivity = new Intent(getApplicationContext(), SensedActivity.class);
                sensedActivity.putExtra("userId", user_id);
                startActivity(sensedActivity);
            }
        });
    }
}
