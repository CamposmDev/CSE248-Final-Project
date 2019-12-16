package com.campos.app;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.campos.util.Sysout;

public class MyAccountActivity extends AppCompatActivity {
    private String username;
    private TextView tfUsername;
    private EditText tfFirst, tfLast, tfEmail;
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
        findViewById(R.id.myAccount_btUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        findViewById(R.id.myAccount_btDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
            }
        });

    }

    private void updateProfile() {
        String username = tfUsername.getText().toString();
        String firstName = tfFirst.getText().toString();
        String lastName = tfLast.getText().toString();
        String email = tfEmail.getText().toString();
        int readingScore = 0;
        int mathScore = 0;
        int writingScore = 0;
        try {
            readingScore = Integer.parseInt(tfReading.getText().toString());
            mathScore = Integer.parseInt(tfMath.getText().toString());
            writingScore = Integer.parseInt(tfWriting.getText().toString());
        } catch (Exception e) {
            AlertUtil.showMessage(this, "Error", "All fields for the SAT scores must be filled in.");
        }
        MyDB.getDb().updateUserAccountByUsername(
                username, firstName, lastName, email, readingScore, mathScore, writingScore);
        String message = "Successfully updated profile!\n";
        message += "First Name: " + firstName + "\n";
        message += "Last Name: " + lastName + "\n";
        message += "Email: " + email + "\n";
        message += "Reading Score: " + readingScore + "\n";
        message += "Math Score: " + mathScore + "\n";
        message += "Writing Score: " + writingScore + "\n";
        AlertUtil.showMessage(this, username, message);
    }

    private void deleteProfile() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Account");
        alert.setMessage("Are you sure you want to delete your account");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDB.getDb().deleteUserAccountByUsername(username);
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    private void initControls() {
        tfUsername = findViewById(R.id.myAccount_username);
        tfFirst = findViewById(R.id.myAccount_firstName);
        tfLast = findViewById(R.id.myAccount_lastName);
        tfEmail = findViewById(R.id.myAccount_email);
        tfReading = findViewById(R.id.myAccount_readingScore);
        tfMath = findViewById(R.id.myAccount_mathScore);
        tfWriting = findViewById(R.id.myAccount_writingScore);
    }

    private void fillFields() {
        Cursor result = MyDB.getDb().findUserAccountByUsername(username);
        result.moveToNext();
        tfUsername.setText(username);
        tfFirst.setText(result.getString(0));
        tfLast.setText(result.getString(1));
        tfEmail.setText(result.getString(2));
        tfReading.setText(result.getString(3));
        tfMath.setText(result.getString(4));
        tfWriting.setText(result.getString(5));
    }
}
