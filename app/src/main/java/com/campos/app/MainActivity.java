package com.campos.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.campos.R;
import com.campos.model.Address;
import com.campos.model.College;
import com.campos.model.SatScores;
import com.campos.model.SatScoresInfo;
import com.campos.model.TuitionInfo;
import com.campos.util.Web;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final int VALID_RESPONSE_CODE = 200;
    private static final String TAG = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListeners();
        startDownloadingData();
    }

    public void initListeners() {
        Button btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.ASSERT, TAG, "Attempting to login...");
            }
        });
        TextView tfSignUp = findViewById(R.id.tfSignUp);
        tfSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void startDownloadingData() {
        try {
            downloadData();
        } catch (IOException e) {
            Log.println(Log.ASSERT, TAG, e.toString());
        }
    }


    public void downloadData() throws IOException {
        String inLine = "";
        URL url = new URL(Web.COLLEGE_SCORECARD_QUERY);
        HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        int responseCode = c.getResponseCode();
        Log.println(Log.ASSERT, TAG, "Response Code: " + responseCode);
        if (responseCode != VALID_RESPONSE_CODE) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inLine = sc.nextLine();
//                Log.println(Log.ASSERT, TAG, inLine);
            }
            sc.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readValue(inLine, JsonNode.class);
            JsonNode resultsArray = node.get("results");
            for (int i = 0; i < resultsArray.size(); i++) {
                emitCollege(resultsArray.get(i));
            }
        }
    }

    public void emitCollege(JsonNode result) {
        JsonNode idNode = result.get("id");
        JsonNode schoolNameNode = result.get("school.name");
        JsonNode regionIdNode = result.get("school.region_id");
        JsonNode schoolZipNode = result.get("school.zip");
        JsonNode schoolCityNode = result.get("school.city");
        JsonNode schoolStateNode = result.get("school.state");
        JsonNode schoolUrlNode = result.get("school.school_url");
        JsonNode tuitionInStateNode = result.get("latest.cost.tuition.in_state");
        JsonNode tuitionOutStateNode = result.get("latest.cost.tuition.out_of_state");
        JsonNode latestStudentSizeNode = result.get("latest.student.size");
        JsonNode admissionRateNode = result.get("latest.admissions.admission_rate.overall");
        JsonNode degreesAwardedNode = result.get("school.degrees_awarded.predominant");
        JsonNode criticalReading25thPercentileNode = result.get("latest.admissions.sat_scores.25th_percentile.critical_reading");
        JsonNode criticalReading75thPercentileNode = result.get("latest.admissions.sat_scores.75th_percentile.critical_reading");
        JsonNode math25thPercentileNode = result.get("latest.admissions.sat_scores.25th_percentile.math");
        JsonNode math75thPercentileNode = result.get("latest.admissions.sat_scores.75th_percentile.math");
        JsonNode writing25thPercentileNode = result.get("latest.admissions.sat_scores.25th_percentile.writing");
        JsonNode writing75thPercentileNode = result.get("latest.admissions.sat_scores.75th_percentile.writing");
        String id = idNode.asText();
        String name = schoolNameNode.asText();
        int regionId = regionIdNode.asInt();
        String zip = schoolZipNode.asText();
        String city = schoolCityNode.asText();
        String state = schoolStateNode.asText();
        String schoolUrl = schoolUrlNode.asText();
        int tuitionInState = tuitionInStateNode.asInt();
        int tuitionOutState = tuitionOutStateNode.asInt();
        int latestStudentSize = latestStudentSizeNode.asInt();
        Address address = new Address(regionId, zip, city, state);
        TuitionInfo tuitionInfo = new TuitionInfo(tuitionInState, tuitionOutState);
        String admissionRate = admissionRateNode.asText();
        int degreesAwarded = degreesAwardedNode.asInt();
        String criticalReading25thPercentile = criticalReading25thPercentileNode.asText();
        String criticalReading75thPercentile = criticalReading75thPercentileNode.asText();
        String math25thPercentile = math25thPercentileNode.asText();
        String math75thPercentile = math75thPercentileNode.asText();
        String writing25thPercentile = writing25thPercentileNode.asText();
        String writing75thPercentile = writing75thPercentileNode.asText();
        SatScores satScores25th = new SatScores(criticalReading25thPercentile, math25thPercentile, writing25thPercentile);
        SatScores satScores75th = new SatScores(criticalReading75thPercentile, math75thPercentile, writing75thPercentile);
        SatScoresInfo satScoresInfo = new SatScoresInfo(satScores25th, satScores75th);
        College college;
        college = new College(id, name, address, schoolUrl, tuitionInfo, latestStudentSize, admissionRate, degreesAwarded, satScoresInfo);
        Log.println(Log.ASSERT, TAG, college.toString());
    }
}
