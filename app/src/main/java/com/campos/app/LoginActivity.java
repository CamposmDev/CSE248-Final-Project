package com.campos.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campos.R;
import com.campos.model.DatabaseHelper;
import com.campos.util.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initListeners();
        dbHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_Github:
                Log.println(Log.ASSERT, "0", "Opening Developer's Github...");
                break;
            case R.id.mi_Help:
                Log.println(Log.ASSERT, "0", "User needs help!");
                break;
        }
        return true;
    }

    public void initListeners() {
        Button btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.ASSERT, "0", "Attempting to login...");
                TextInputEditText tfUsername = findViewById(R.id.login_textInputUsername);
                EditText tfPassword = findViewById(R.id.login_tfPassword);
                String username = tfUsername.getText().toString();
                String password = tfPassword.getText().toString();
                Log.println(Log.ASSERT, "0", "User entered: " + username + " | " + password);
            }
        });
        TextView tfSignUp = findViewById(R.id.tfSignUp);
        tfSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
