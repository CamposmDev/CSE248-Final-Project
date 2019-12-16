package com.campos.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.campos.R;
import com.campos.model.State;
import com.campos.util.AlertHelper;
import com.campos.util.MyDB;
import com.campos.util.Sysout;

import java.util.ArrayList;
import java.util.Collections;

public class CollegeFinderActivity extends AppCompatActivity {
    private String username;
    private int menOnly;
    private int womenOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_finder);
        username = getIntent().getStringExtra("USERNAME");
        Sysout.println(username);
        initListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_MyAccount:
                AlertHelper.showMessage(this, "WIP", "Not Implemented Yet");
//                Intent intentMyAccount = new Intent(this, MyAccountActivity.class);
//                intentMyAccount.putExtra("USERNAME", username);
//                startActivity(intentMyAccount);
                break;
            case R.id.mi_Signout:
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
                break;
        }
        return true;
    }

    private void initListeners() {
        State.fillSpinner((Spinner) findViewById(R.id.cf_spinnerState), this);
        Button btFindCollege = findViewById(R.id.cf_btFindMyCollege);
        btFindCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findColleges();
            }
        });
        RadioButton btMen = findViewById(R.id.cf_radioButton_Men);
        btMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menOnly = 1;
                womenOnly = 0;
            }
        });
        RadioButton btWomen = findViewById(R.id.cf_radioButton_Women);
        btWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menOnly = 0;
                womenOnly = 1;
            }
        });
        RadioButton btBoth = findViewById(R.id.cf_radioButton_Both);
        btBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menOnly = 1;
                womenOnly = 1;
            }
        });
    }

    private void findColleges() {
        String collegeName = ((EditText) findViewById(R.id.cf_CollegeName)).getText().toString();
        String city = ((EditText) findViewById(R.id.cf_City)).getText().toString();
        int zip = 0;
        try {
            zip = Integer.parseInt(((EditText) findViewById(R.id.cf_Zip)).getText().toString());
        } catch (Exception e) {
            zip = -1;
        }
        String state = ((Spinner) findViewById(R.id.cf_spinnerState)).getSelectedItem().toString();
        Sysout.println(collegeName);
        Sysout.println(city);
        Sysout.println(zip);
        Sysout.println(state);
        String conditions = null;

        if (!collegeName.isEmpty()) {
            conditions = "name LIKE \'%" + collegeName + "%\'";
            if (!city.isEmpty()) {
                conditions += " AND city LIKE \'%" + city + "%\' AND menOnly=" + menOnly + " AND womenOnly=" + womenOnly;
            }
            if (zip != -1) {
                conditions += " AND zip=\'" + zip + "\'";
            }
            if (!state.equals(State.All.toString())) {
                conditions += " AND state=\'" + state + "\'";
            }
            collectResults(conditions);
        } else {
            AlertHelper.showMessage(this, "Missing Information D:", "Not enough information to find colleges.");
        }
    }

    private void collectResults(String conditions) {
        ArrayList<String> list = new ArrayList<>();
        Cursor result = MyDB.getDb().findCollegeByConditions(conditions);
        Sysout.println("Results: " + result.getCount());
        if (result.getCount() != 0) {
            while (result.moveToNext()) {
                String name = result.getString(result.getColumnIndex("name"));
                Sysout.println(name);
                list.add(name);
            }
        }
        result.close();
        Collections.sort(list);
        Sysout.println(list.toString());
        Intent intent = new Intent(this, CollegeListViewerActivity.class);
        intent.putExtra("USERNAME", username);
        intent.putExtra("COLLEGE_NAMES", list);
        startActivity(intent);
    }
}
