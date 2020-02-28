package com.campos.util;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class DBLoader extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        try {
            downloadData();
        } catch (Exception e) {
            Sysout.println(e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

    }

    public static URL connectToCollegeScorecard(int pageNumber) throws IOException {
        URL url = new URL(Web.COLLEGE_SCORECARD_QUERY + "&page=" + pageNumber);
        HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        return url;
    }

    public static int getTotalPages() throws IOException {
        URL url = connectToCollegeScorecard(0);
        String inLine = "";
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext()) {
            inLine = sc.nextLine();
        }
        sc.close();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(inLine, JsonNode.class);
        JsonNode metaDataNode = node.get("metadata");
        Sysout.println(metaDataNode.toString());
        int totalPages = metaDataNode.get("total").asInt();
        return totalPages;
    }

    public static void downloadData() throws IOException {
        String inLine = "";
        int totalPages = getTotalPages();
        for (int i = 0; i < totalPages; i++) {
            URL url = connectToCollegeScorecard(i);
            Scanner in = new Scanner(url.openStream());
            while (in.hasNext()) {
                inLine = in.nextLine();
            }
            in.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readValue(inLine, JsonNode.class);
            JsonNode resultsArray = node.get("results");
            Sysout.println(resultsArray.toString());
            for (int j = 0; j < resultsArray.size(); j++) {
                emitCollege(resultsArray.get(j));
            }
        }
        Sysout.println("Finished downloading college data");
    }

    public static void emitCollege(JsonNode result) {
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
//        Address address = new Address(regionId, zip, city, state);
//        TuitionInfo tuitionInfo = new TuitionInfo(tuitionInState, tuitionOutState);
        double admissionRate = -1;
        int degreesAwarded = degreesAwardedNode.asInt();
        int readingScore25th = -1;
        int readingScore75th = -1;
        int mathScore25th = -1;
        int mathScore75th = -1;
        int writingScore25th = -1;
        int writingScore75th = -1;
        try {
            admissionRate = admissionRateNode.asDouble();
            readingScore25th = criticalReading25thPercentileNode.asInt();
            readingScore75th = criticalReading75thPercentileNode.asInt();
            mathScore25th = math25thPercentileNode.asInt();
            mathScore75th = math75thPercentileNode.asInt();
            writingScore25th = writing25thPercentileNode.asInt();
            writingScore75th = writing75thPercentileNode.asInt();
        } catch (Exception e) {
            Sysout.println(e.toString());
        }
        int menOnly = result.get("school.men_only").asInt(); // 0 = No, 1 = Yes
        int womenOnly = result.get("school.women_only").asInt();
//        SatScores satScores25th = new SatScores(readingScore25th, mathScore25th, writingScore25th);
//        SatScores satScores75th = new SatScores(readingScore75th, mathScore75th, writingScore75th);
//        SatScoresInfo satScoresInfo = new SatScoresInfo(satScores25th, satScores75th);
//        College college;
//        college = new College(id, name, address, schoolUrl, tuitionInfo, latestStudentSize, admissionRate, degreesAwarded, satScoresInfo, menOnly, womenOnly);
//        Sysout.println(college.toString());
        MyDB.getDb().addCollege(id, name, regionId, zip, city, state, schoolUrl,
                latestStudentSize, tuitionInState, tuitionOutState, admissionRate, degreesAwarded,
                readingScore25th, readingScore75th, mathScore25th, mathScore75th, writingScore25th, writingScore75th, menOnly, womenOnly);
    }
}
