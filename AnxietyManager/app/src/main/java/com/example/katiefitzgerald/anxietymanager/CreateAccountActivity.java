package com.example.katiefitzgerald.anxietymanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pub.devrel.easypermissions.EasyPermissions;

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

        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.password);
        chosenAccount = findViewById(R.id.isCounsellor);
        createAccount = findViewById(R.id.createBtn);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void addUser() {
        String userEmail = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();

        Intent home = new Intent(getApplicationContext(), HomeActivity.class);

        if(userEmail.matches("")){
            Toast.makeText(this, "Please fill in a email address", Toast.LENGTH_LONG).show();
        }
        else if (password.matches("")){
            Toast.makeText(this, "Please fill in a password", Toast.LENGTH_LONG).show();
        }
        else {
            if(chosenAccount.isChecked()) {
                startActivity(home);
                setContentView(R.layout.activity_home);

                Toast.makeText(this, "Counsellor", Toast.LENGTH_LONG).show();
            }
            else {
                String user_id = databaseUsers.push().getKey();
                UserDao user = new UserDao(user_id, userEmail, password);

                databaseUsers.child(user_id).setValue(user);

                Toast.makeText(this, "New account created: " + userEmail, Toast.LENGTH_LONG).show();

                home.putExtra("user_id", user_id);
                startActivity(home);
                setContentView(R.layout.activity_home);

            }
        }
    }
}
