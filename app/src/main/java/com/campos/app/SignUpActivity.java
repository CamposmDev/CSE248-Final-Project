package com.campos.app;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.campos.R;
import com.campos.util.MyDB;
import com.campos.util.AlertUtil;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initListeners();
    }

    private void initListeners() {
        Button btSignUp = findViewById(R.id.signup_btSignup);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserAccount();
            }
        });
    }

    public void createUserAccount() {
        String firstName = ((TextInputEditText) findViewById(R.id.signup_tfFirstName)).getText().toString();
        String lastName = ((TextInputEditText) findViewById(R.id.signup_tfLastName)).getText().toString();
        String email = ((TextInputEditText) findViewById(R.id.signup_tfEmail)).getText().toString();
        int readingScore = 0;
        int mathScore = 0;
        int writingScore = 0;
        try {
            readingScore = Integer.parseInt(((EditText) findViewById(R.id.signup_tfReading)).getText().toString());
            mathScore = Integer.parseInt(((EditText) findViewById(R.id.signup_tfMath)).getText().toString());
            writingScore = Integer.parseInt(((EditText) findViewById(R.id.signup_tfWriting)).getText().toString());
        } catch (Exception e) {

        }
        String username = ((TextInputEditText) findViewById(R.id.signup_tfUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.signup_tfPassword)).getText().toString();

        if(username.isEmpty()) {
            AlertUtil.showMessage(this, "Invalid Username", "Your username can't be empty");
        } else if (password.isEmpty()) {
            AlertUtil.showMessage(this, "Invalid Password", "Your password can't be empty");
        } else {
            Cursor result = MyDB.getDb().findUserAccountByUsername(username);
            if (result.getCount() == 0) { // Check if unique username
                MyDB.getDb().addUserAccount(firstName, lastName, email, readingScore, mathScore, writingScore, username, password);
                AlertUtil.showToast(this, "Successfully signed up! :D", Toast.LENGTH_LONG);
                finish();
            } else { // Not a unique username
                AlertUtil.showMessage(this, "Invalid Username", "The username: " + username + " is already taken!");
            }
        }
    }
}
