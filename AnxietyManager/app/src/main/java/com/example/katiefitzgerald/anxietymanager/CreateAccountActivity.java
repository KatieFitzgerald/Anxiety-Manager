package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateAccountActivity extends SensedActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText enterEmail;
    EditText enterPassword;
    CheckBox chosenAccount;
    Button createAccount;
    TextView login;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        databaseUsers = FirebaseDatabase.getInstance().getReference("user_profile");

        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.password);
        chosenAccount = findViewById(R.id.isCounsellor);
        createAccount = findViewById(R.id.createBtn);
        login = findViewById(R.id.login);

        sharedPreferences = getApplicationContext().getSharedPreferences("Create", 0);
        editor = sharedPreferences.edit();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent LoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LoginActivity);
            }
        });

    }

    private void addUser() {
        String userEmail = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();

        Intent login = new Intent(getApplicationContext(), LoginActivity.class);

        if(userEmail.length() < 0){
            Toast.makeText(this, "Please fill in a email address", Toast.LENGTH_LONG).show();
            return;
        }
        else if (password.length() < 0){
            Toast.makeText(this, "Please fill in a password", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            if(chosenAccount.isChecked()) {
                editor.putString("Email", userEmail);
                editor.putString("Password", password);
                editor.commit();

                Toast.makeText(this, "New account created: " + userEmail, Toast.LENGTH_LONG).show();

                startActivity(login);
                //setContentView(R.layout.activity_home);

                Toast.makeText(this, "Counsellor", Toast.LENGTH_LONG).show();
            }
            else {
                editor.putString("Email", userEmail);
                editor.putString("Password", password);
                editor.commit();

                String user_id = databaseUsers.push().getKey();
                UserDao user = new UserDao(user_id, userEmail, password);

                databaseUsers.child(user_id).setValue(user);

                Toast.makeText(this, "New account created: " + userEmail, Toast.LENGTH_LONG).show();

                //home.putExtra("userId", user_id);
                startActivity(login);
                //setContentView(R.layout.activity_home);

            }
        }
    }

}
