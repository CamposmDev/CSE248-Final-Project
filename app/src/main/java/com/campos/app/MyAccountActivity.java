package com.campos.app;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.campos.R;
import com.campos.util.MyDB;

public class MyAccountActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        username = getIntent().getStringExtra("USERNAME");
        fillFields();
        addListeners();
    }

    private void addListeners() {
        Button btChangePassword = findViewById(R.id.myAccount_btChangePassword);
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button btUpdate = findViewById(R.id.myAccount_btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void fillFields() {
        Cursor result = MyDB.getDb().findUserAccount(username);
        ((TextView) findViewById(R.id.myAccount_username)).setText(username);
        ((EditText) findViewById(R.id.myAccount_firstName)).setText(result.getString(0));
        ((EditText) findViewById(R.id.myAccount_lastName)).setText(result.getString(1));
    }
}
