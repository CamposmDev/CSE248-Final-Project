package com.campos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String QUERY = "https://api.data.gov/ed/collegescorecard/v1/schools.json?&school.degrees_awarded.predominant=2,3&fields=id,school.name,school.city,school.state,school.zip,school.school_url,latest.student.size&api_key=eymRFR4vdKAgPCK3JIw9Es42ytaEelgZf43H5TKc&_per_page=100&_zip=11784&_distance=10";
    private static final int VALID_RESPONSE_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            methodA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void methodA() throws Exception {
        String inLine = "";
        URL url = new URL(QUERY);
        HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        int responseCode = c.getResponseCode();
        if (responseCode != VALID_RESPONSE_CODE) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            System.out.println("Valid Response Code: " + responseCode);
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
