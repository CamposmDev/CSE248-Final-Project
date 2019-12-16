package com.campos.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.campos.R;
import com.campos.model.State;

public class CollegeFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_finder);
        initListeners();
    }

    private void initListeners() {
        State.fillSpinner((Spinner) findViewById(R.id.cf_spinnerState), this);
        Button btFindCollege = findViewById(R.id.cf_btFindMyCollege);
        btFindCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCollege();
            }
        });
    }

    private void findCollege() {

    }
}
