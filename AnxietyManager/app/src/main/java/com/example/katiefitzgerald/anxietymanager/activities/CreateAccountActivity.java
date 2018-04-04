package com.example.katiefitzgerald.anxietymanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.User;
import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLException;

import pub.devrel.easypermissions.EasyPermissions;

public class CreateAccountActivity extends AppCompatActivity {

    EditText enterEmail;
    EditText enterPassword;
    EditText enterName;
    EditText confirmPassword;
    CheckBox chosenAccount;
    Button createAccount;
    TextView login;

    DatabaseManager db = new DatabaseManager(this);
    DatabaseReference UsersDB = FirebaseDatabase.getInstance().getReference("user_profile");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        enterName = findViewById(R.id.enterName);
        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.enterPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        chosenAccount = findViewById(R.id.isCounsellor);
        createAccount = findViewById(R.id.createBtn);
        login = findViewById(R.id.loginText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
            }
        });

        createAccount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        createAccount.setBackgroundResource(R.drawable.pressed);
                        createAccount.setTextColor(Color.GRAY);
                        addUser();
                        return true;
                    case MotionEvent.ACTION_UP:
                        createAccount.setBackgroundResource(R.drawable.shape);
                        createAccount.setTextColor(Color.WHITE);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public void onBackPressed() { }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void addUser() {

        String userName = enterName.getText().toString().trim();
        String userEmail = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();
        String conPassword = confirmPassword.getText().toString().trim();

        if(userEmail.matches("")){
            Toast.makeText(this, "Please fill in a email address", Toast.LENGTH_LONG).show();
        }
        else if (password.matches("")){
            Toast.makeText(this, "Please fill in a password", Toast.LENGTH_LONG).show();
        }
        else if (!password.matches(conPassword)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
        }
        else if (db.checkUser(userEmail, password)){
            Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show();
        }
        else {
            if(chosenAccount.isChecked()) {

                String user_id = UsersDB.push().getKey();

                User user = new User(user_id, userName, userEmail, password, false);

                UsersDB.child(user_id).setValue(user);

                try {

                    db.open();
                    db.addUser(user);

                }
                catch (SQLException e){

                    Toast.makeText(CreateAccountActivity.this, "Error adding user into database", Toast.LENGTH_SHORT).show();

                }

                //start counsellor activity
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                home.putExtra("user_id", user_id);
                startActivity(home);
                Toast.makeText(this, "Counsellor", Toast.LENGTH_LONG).show();
            }
            else {

                String user_id = UsersDB.push().getKey();

                User user = new User(user_id, userName, userEmail, password, false);

                UsersDB.child(user_id).setValue(user);

                try {

                    db.open();
                    db.addUser(user);

                }
                catch (SQLException e){

                    Toast.makeText(CreateAccountActivity.this, "Error adding user into database", Toast.LENGTH_SHORT).show();

                }

                //start home activity
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                home.putExtra("user_id", user_id);
                startActivity(home);
            }
        }
    }
}