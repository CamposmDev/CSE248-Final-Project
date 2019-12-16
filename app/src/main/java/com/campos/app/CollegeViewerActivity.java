package com.campos.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.campos.R;
import com.campos.model.SatScores;
import com.campos.model.SatScoresInfo;
import com.campos.util.MyDB;
import com.campos.util.Sysout;

public class CollegeViewerActivity extends AppCompatActivity {
    private String name;
    private String schoolUrl;
    private Cursor result;
    private SatScoresInfo satScoresInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_viewer);
        name = getIntent().getStringExtra("COLLEGE_NAME");
        result = MyDB.getDb().findCollegeByName(name);
        try {
            fillDataFields();
            initListeners();
        } catch (Exception e) {
            Sysout.println(e.toString());
        }
    }

    private void initListeners() {
//        TextView tfUrl = findViewById(R.id.cv_lblSchoolUrl);
//        tfUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(schoolUrl));
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                startActivity(intent);
//            }
//        });
        Button btCompare = findViewById(R.id.cv_btCompare);
        btCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sysout.println("Comparing SAT Scores...");
                Cursor result = MyDB.getDb().findUserAccount(getIntent().getStringExtra("USERNAME"));
                result.moveToNext();
                int readingScore = result.getInt(result.getColumnIndex("readingScore"));
                int mathScore = result.getInt(result.getColumnIndex("mathScore"));
                int writingScore = result.getInt(result.getColumnIndex("writingScore"));
                SatScores satScores = new SatScores(readingScore, mathScore, writingScore);
                AlertDialog.Builder dialog =
            }
        });
    }

    private void fillDataFields() {
        result.moveToNext();

        String id = result.getString(result.getColumnIndex("id"));
        String regionId = result.getString(result.getColumnIndex("regionId"));
        String city = result.getString(result.getColumnIndex("city"));
        String zip = result.getString(result.getColumnIndex("zip"));
        String state = result.getString(result.getColumnIndex("state"));
        schoolUrl = result.getString(result.getColumnIndex("url"));
        String latestStudentSize = result.getString(result.getColumnIndex("latestStudentSize"));
        String tuitionInState = result.getString(result.getColumnIndex("tuitionInState"));
        String tuitionOutOfState = result.getString(result.getColumnIndex("tuitionOutOfState"));
        String admissionsRate = result.getString(result.getColumnIndex("admissionsRate"));
        String degreesAwarded = result.getString(result.getColumnIndex("degreesAwarded"));
        int readingScore25th = result.getInt(result.getColumnIndex("readingScore25th"));
        int readingScore75th = result.getInt(result.getColumnIndex("readingScore75th"));
        int mathScore25th = result.getInt(result.getColumnIndex("mathScore25th"));
        int mathScore75th = result.getInt(result.getColumnIndex("mathScore75th"));
        int writingScore25th = result.getInt(result.getColumnIndex("writingScore25th"));
        int writingScore75th = result.getInt(result.getColumnIndex("writingScore75th"));
        int menOnly = result.getInt(result.getColumnIndex("menOnly"));
        int womenOnly = result.getInt(result.getColumnIndex("womenOnly"));

        SatScores sat25th = new SatScores(readingScore25th, mathScore25th, writingScore25th);
        SatScores sat75th = new SatScores(readingScore75th, mathScore75th, writingScore75th);
        satScoresInfo = new SatScoresInfo(sat25th, sat75th);

        TextView tfName = findViewById(R.id.cv_name);
        tfName.setText(name);
        TextView tfId = findViewById(R.id.cv_Id);
        tfId.append(id);
        TextView tfRegionId = findViewById(R.id.cv_regionId);
        tfRegionId.append(regionId);
        TextView tfCity = findViewById(R.id.cv_lblCity);
        tfCity.append(city);
        TextView tfZip = findViewById(R.id.cv_lblZip);
        tfZip.append(zip);
        TextView tfState = findViewById(R.id.cv_lblState);
        tfState.append(state);
        TextView tfUrl = findViewById(R.id.cv_lblSchoolUrl);
        tfUrl.append(schoolUrl);
//        tfUrl.setTextColor(Color.BLUE);

        TextView tfTuitionInState = findViewById(R.id.cv_tuitionInState);
        tfTuitionInState.append(tuitionInState);
        TextView tfTuitonOutOfState = findViewById(R.id.cv_tuitionOutOfState);
        tfTuitonOutOfState.append(tuitionOutOfState);

        TextView tfReading25th = findViewById(R.id.cv_reading25th);
        tfReading25th.append(readingScore25th + "");
        TextView tfReading75th = findViewById(R.id.cv_reading75th);
        tfReading75th.append(readingScore75th + "");
        TextView tfMath25th = findViewById(R.id.cv_math25th);
        tfMath25th.append(mathScore25th + "");
        TextView tfMath75th = findViewById(R.id.cv_math75th);
        tfMath75th.append(mathScore75th + "");
        TextView tfWriting25th = findViewById(R.id.cv_writing25th);
        tfWriting25th.append(writingScore25th + "");
        TextView tfWriting75th = findViewById(R.id.cv_writing75th);
        tfWriting75th.append(writingScore75th + "");

        result.close();
    }
}
