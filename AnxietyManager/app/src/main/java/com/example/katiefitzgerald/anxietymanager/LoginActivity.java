package com.example.katiefitzgerald.anxietymanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Katie Fitzgerald on 25/02/2018.
 */

public class LoginActivity extends Activity {

    private static final String PREFER_NAME = "Create";

    EditText enterEmail;
    EditText enterPassword;
    Button login;

    UserSession session;

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSession(getApplicationContext());

        // get Email, Password input text
        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.password);

        login = findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = enterEmail.getText().toString().trim();
                String password = enterPassword.getText().toString().trim();

                if (userEmail.matches("")) {
                    //Toast.makeText(this, "Please fill in a email address", Toast.LENGTH_LONG).show();
                    return;
                } else if (password.matches("")) {
                    //Toast.makeText(this, "Please fill in a password", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    String uEmail = null;
                    String uPassword = null;

                    if (sharedPreferences.contains("Email")) {
                        uEmail = sharedPreferences.getString("Email", "");

                    }

                    if (sharedPreferences.contains("Password")) {
                        uPassword = sharedPreferences.getString("Password", "");

                    }
                    // Object uName = null;
                    // Object uEmail = null;
                    if (userEmail.equals(uEmail) && password.equals(uPassword)) {

                        session.createUserLoginSession(userEmail, uPassword);

                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);


                    }

                }

            }
        });

    }
}
