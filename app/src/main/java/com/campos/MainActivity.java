package com.campos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String QUERY = "https://api.data.gov/ed/collegescorecard/v1/schools.json?&fields=id,school.region_id,school.name,school.city,school.state,school.zip,school.school_url,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state,latest.student.size,latest.admissions.admission_rate.overall,school.degrees_awarded.predominant,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math&api_key=eymRFR4vdKAgPCK3JIw9Es42ytaEelgZf43H5TKc&_per_page=100";
    private static final int VALID_RESPONSE_CODE = 200;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        methodB();
    }

    public void methodB() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    methodA();
                } catch (Exception e) {
                    Log.println(Log.ASSERT, TAG, e.toString());
                }
            }
        });
        t.start();
    }


    public void methodA() throws Exception {
        String inLine = "";
        URL url = new URL(QUERY);
        HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        int responseCode = c.getResponseCode();
        Log.println(Log.ASSERT, TAG, "Valid Response Code: " + responseCode);
        if (responseCode != VALID_RESPONSE_CODE) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inLine = sc.nextLine();
				System.out.println(inLine);
            }
            sc.close();
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode node = objectMapper.readValue(inLine, JsonNode.class);
//            JsonNode resultsArray = node.get("results");
//            for (int i = 0; i < resultsArray.size(); i++) {
//                JsonNode result = resultsArray.get(i);
//                JsonNode idNode = result.get("id");
//                JsonNode schoolNameNode = result.get("school.name");
//                JsonNode schoolCityNode = result.get("school.city");
//                JsonNode schoolStateNode = result.get("school.state");
//                JsonNode schoolZipNode = result.get("school.zip");
//                JsonNode schoolUrlNode = result.get("school.school_url");
//                JsonNode latestStudentSizeNode = result.get("latest.student.size");
//                String id = idNode.asText();
//                String name = schoolNameNode.asText();
//                String city = schoolCityNode.asText();
//                String state = schoolStateNode.asText();
//                String zip = schoolZipNode.asText();
//                String schoolUrl = schoolUrlNode.asText();
//                int latestStudentSize = latestStudentSizeNode.asInt();
//                School school;
//                school = new School(id, name, city, state, zip, schoolUrl, latestStudentSize);
        }
    }
}
