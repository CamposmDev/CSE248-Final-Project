package com.campos.app;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.campos.R;
import com.campos.util.AlertUtil;
import com.campos.util.MyDB;

public class MyAccountActivity extends AppCompatActivity {
    private String username;
    private TextView tfUsername;
    private EditText tfFirst, tfLast;
    private EditText tfReading, tfMath, tfWriting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        username = getIntent().getStringExtra("USERNAME");
        initControls();
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

    private void changePassword() {
        AlertUtil.showMessage(this, "Change Password", "Not Implemented Yet");
    }

    private void updateProfile() {
        AlertUtil.showMessage(this, "Update", "Successfully updated profile!");
    }

    private void deleteProfile() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Account");
        alert.setMessage("Are you sure you want to delete your account");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setNegativeButton("No", null);
    }

    private void initControls() {
        tfUsername = findViewById(R.id.myAccount_username);
        tfFirst = findViewById(R.id.myAccount_firstName);
        tfLast = findViewById(R.id.myAccount_lastName);
        tfReading = findViewById(R.id.myAccount_readingScore);
        tfMath = findViewById(R.id.myAccount_mathScore);
        tfWriting = findViewById(R.id.myAccount_writingScore);
    }

    private void fillFields() {
        Cursor result = MyDB.getDb().findUserAccountByUsername(username);
        result.moveToNext();
        ((TextView) findViewById(R.id.myAccount_username)).setText(username);
        ((EditText) findViewById(R.id.myAccount_firstName)).setText(result.getString(0));
        ((EditText) findViewById(R.id.myAccount_lastName)).setText(result.getString(1));
    }
}
