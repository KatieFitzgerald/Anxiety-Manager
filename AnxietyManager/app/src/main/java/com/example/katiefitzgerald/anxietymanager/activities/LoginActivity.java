package com.example.katiefitzgerald.anxietymanager.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Katie Fitzgerald on 15/03/2018.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.sql.DatabaseManager;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    TextView register;

    DatabaseManager databaseManager = new DatabaseManager(this);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.enterEmail);
        password = findViewById(R.id.enterPassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(register);
            }
        });

        checkLogin();

    }

    private void checkLogin(){

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String enteredEmail = email.getText().toString();
                final String enteredPassword = password.getText().toString();

                login.setBackgroundResource(R.drawable.pressed);
                login.setTextColor(Color.GRAY);

                if(null!= enteredEmail && enteredEmail.length() == 0) {

                    final TextView warning = findViewById(R.id.emailWarning);
                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter an email address");

                    email.addTextChangedListener(new TextWatcher()
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
                else if (enteredPassword.length() == 0){

                    final TextView warning = findViewById(R.id.passwordWarning);
                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter a password");

                    password.addTextChangedListener(new TextWatcher()
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
                else {
                    if(databaseManager.checkUser(enteredEmail, enteredPassword)) {

                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        home.putExtra("EMAIL", enteredEmail);
                        startActivity(home);
                    }
                    else {
                        final TextView warning = findViewById(R.id.passwordWarning);
                        warning.setTextColor(Color.RED);
                        warning.setText("Login credentials are incorrect");

                        email.setText(null);
                        password.setText(null);
                        checkLogin();
                    }

                }
            }
        });

        login.setBackgroundResource(R.drawable.shape);
        login.setTextColor(Color.WHITE);
    }
}
