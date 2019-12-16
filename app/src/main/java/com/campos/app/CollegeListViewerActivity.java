package com.campos.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.campos.R;
import com.campos.util.Sysout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CollegeListViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list_viewer);
        ArrayList<String> list = (ArrayList<String>) getIntent().getExtras().get("COLLEGE_NAMES");
        final ListView lv = findViewById(R.id.clv_listview);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String collegeName = (String) lv.getAdapter().getItem(position);
                Sysout.println("User selected: " + collegeName);
                Intent intent = new Intent(CollegeListViewerActivity.this, CollegeViewerActivity.class);
                intent.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
                intent.putExtra("COLLEGE_NAME", collegeName);
                startActivity(intent);
            }
        });
    }
}
