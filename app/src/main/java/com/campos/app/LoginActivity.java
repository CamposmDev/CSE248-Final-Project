package com.campos.app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campos.R;
import com.campos.util.MyDB;
import com.campos.util.AlertUtil;
import com.campos.util.Sysout;
import com.campos.util.Web;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_Github:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Web.MY_GITHUB));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);
                break;
            case R.id.mi_Help:
                Log.println(Log.ASSERT, "0", "User needs help!");
                break;
        }
        return true;
    }

    public void initListeners() {
        Button btLogin = findViewById(R.id.login_btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });
        TextView tfSignUp = findViewById(R.id.login_tfSignUp);
        tfSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }

    private void attemptToLogin() {
        EditText tfUsername = findViewById(R.id.login_tfUsername);
        EditText tfPassword = findViewById(R.id.login_tfPassword);
        String usernameEntered = tfUsername.getText().toString();
        String passwordEntered = tfPassword.getText().toString();
        Sysout.println("User entered: " + usernameEntered + ", " + passwordEntered);
        Cursor res = MyDB.getDb().findUserAccountByUsername(usernameEntered);
//        Sysout.println(res.getCount());
        if (res.getCount() != 0) { // We found the username
            res.moveToNext();
            String password = res.getString(7);
            res.close();
            if (password.equals(passwordEntered)) { // Check if the passwords match
                openCollegeFinderActivity(usernameEntered);
                finish();
            } else {
                AlertUtil.showMessage(this, "Invalid Password!", "Password does not match!");
            }
        } else {
            AlertUtil.showMessage(this, "Invalid Login!", "The username you entered does not exist!");
        }
    }

    private void openCollegeFinderActivity(String username) {
        Intent intent = new Intent(this, CollegeFinderActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
