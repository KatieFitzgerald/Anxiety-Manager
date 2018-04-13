package com.example.katiefitzgerald.anxietymanager.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Katie Fitzgerald on 15/03/2018.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.katiefitzgerald.anxietymanager.R;
import com.example.katiefitzgerald.anxietymanager.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button login;
    TextView register;
    TextView warning;
    CoordinatorLayout coordinatorLayout;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.enterUsername);
        passwordEditText = findViewById(R.id.enterPassword);
        warning = findViewById(R.id.passwordWarning);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerText);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
            }
        });

        checkLogin();

    }

    private void checkLogin(){

        login.setBackgroundResource(R.drawable.shape);
        login.setTextColor(Color.WHITE);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String enteredUsername = usernameEditText.getText().toString().trim();
                final String enteredPassword = passwordEditText.getText().toString().trim();

                login.setBackgroundResource(R.drawable.pressed);
                login.setTextColor(Color.GRAY);

                if(null!= enteredUsername && enteredUsername.length() == 0) {

                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter a username");

                    usernameEditText.addTextChangedListener(new TextWatcher()
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

                    warning.setTextColor(Color.RED);
                    warning.setText("Please enter a password");

                    passwordEditText.addTextChangedListener(new TextWatcher()
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

                    //Check user_profile of name and password entered
                    DatabaseReference UsersDB = FirebaseDatabase.getInstance().getReference();
                    Query userReference = UsersDB.child("user_profile").orderByChild("name").equalTo(enteredUsername);
                    final Query passwordReference = UsersDB.child("user_profile").orderByChild("password").equalTo(enteredPassword);

                    userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null) {

                                passwordReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.getValue() != null){

                                            for(DataSnapshot userData: dataSnapshot.getChildren()) {

                                                User user = userData.getValue(User.class);
                                                String user_id = user.getId();

                                                if (user.getCounsellor()){

                                                    Intent home = new Intent(getApplicationContext(), ChoosePatientActivity.class);
                                                    home.putExtra("user_id", user_id);
                                                    startActivity(home);

                                                }
                                                else {

                                                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                                                    home.putExtra("user_id", user_id);
                                                    startActivity(home);
                                                }
                                            }

                                        }
                                        else {

                                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Incorrect login credentials", Snackbar.LENGTH_LONG);
                                            View view = snackbar.getView();
                                            TextView snackbarText = view.findViewById(android.support.design.R.id.snackbar_text);
                                            snackbarText.setTextColor(Color.parseColor("#f04343"));
                                            snackbar.show();

                                            usernameEditText.setText(null);
                                            passwordEditText.setText(null);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            else {

                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Incorrect login credentials", Snackbar.LENGTH_LONG);
                                View view = snackbar.getView();
                                TextView snackbarText = view.findViewById(android.support.design.R.id.snackbar_text);
                                snackbarText.setTextColor(Color.parseColor("#f04343"));
                                snackbar.show();

                                usernameEditText.setText(null);
                                passwordEditText.setText(null);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        login.setBackgroundResource(R.drawable.shape);
        login.setTextColor(Color.WHITE);
    }
}
