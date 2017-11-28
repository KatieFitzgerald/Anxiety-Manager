package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends SensedActivity {

    EditText enterEmail;
    EditText enterPassword;
    CheckBox chosenAccount;
    Button createAccount;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        databaseUsers = FirebaseDatabase.getInstance().getReference("user_profile");

        enterEmail = (EditText) findViewById(R.id.enterEmail);
        enterPassword = (EditText) findViewById(R.id.password);
        chosenAccount = (CheckBox) findViewById(R.id.isCounsellor);
        createAccount = (Button) findViewById(R.id.createBtn);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    private void addUser() {
        String userEmail = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();


        Intent home = new Intent(getApplicationContext(), HomeActivity.class);

        if(!TextUtils.isEmpty(userEmail) || !TextUtils.isEmpty(password)){
            if(chosenAccount.isChecked()) {
                Toast.makeText(this, "You are a counsellor " + userEmail, Toast.LENGTH_LONG).show();
                startActivity(home);
                setContentView(R.layout.activity_home);
            }
            else {
                startActivity(home);
                setContentView(R.layout.activity_home);

                String id = databaseUsers.push().getKey();
                UserDao user = new UserDao(id, userEmail, password);

                databaseUsers.child(id).setValue(user);

                Toast.makeText(this, "New account created: " + userEmail, Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
        }
    }
}
