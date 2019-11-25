package com.campos.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.campos.R;
import com.campos.util.Web;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final int VALID_RESPONSE_CODE = 200;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        startDownloadOfData();
    }

    public void startDownloadOfData() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downloadData();
                } catch (Exception e) {
                    Log.println(Log.ASSERT, TAG, e.toString());
                }
            }
        });
        t.start();
    }


    public void downloadData() throws Exception {
        String inLine = "";
        URL url = new URL(Web.COLLEGE_SCORECARD_QUERY);
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
