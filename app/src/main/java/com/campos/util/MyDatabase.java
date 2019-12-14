package com.campos.util;

import android.util.Log;

import com.campos.model.Address;
import com.campos.model.College;
import com.campos.model.SatScores;
import com.campos.model.SatScoresInfo;
import com.campos.model.TuitionInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MyDatabase {
    private static final String CONNECT_TO_DB = "jdbc:sqlite:CollegeNavigatorDB.sqlite";

    public static Connection connectToDB() {
        try {
            Class.forName("org.sqlite. JDBC");
            Connection conn = DriverManager.getConnection(CONNECT_TO_DB);
            return conn;
        } catch (SQLException e) {
            Log.println(Log.ASSERT, "0", e.getSQLState());
        } catch (ClassNotFoundException e) {
            Log.println(Log.ASSERT, "0", e.getMessage());
        }
        return null;
    }

    public static void createCollegeNavigatorDB() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("drop table if exists College");
        stmt.executeUpdate("drop table if exists UserAccount");
        stmt.executeUpdate("create table College(" +
                "id VARCHAR NOT_NULL, " +
                "name VARCHAR, " +
                "regionId int, " +
                "zip VARCHAR, " +
                "city VARCHAR, " +
                "state VARCHAR(4), " +
                "url VARCHAR, " +
                "tuitionInState int, " +
                "tuitionOutOfState int, " +
                "admissionsRate decimal, " +
                "degreesAwarded int" +
                ");");
        stmt.executeUpdate("create table UserAccount(" +
                "firstName VARCHAR(64), " +
                "lastName VARCHAR(64), " +
                "email VARCHAR(256), " +
                "readingScore int, " +
                "mathScore int, " +
                "writingScore int, " +
                "username VARCHAR(64) PRIMARY KEY, " +
                "password VARCHAR(16))");
        conn.close();
        fillDBWithData();
    }

    public static void fillDBWithData() {
        try {
            downloadData();
        } catch (IOException e) {
            Log.println(Log.ASSERT, "0", e.toString());
        }
    }

    public static void downloadData() throws IOException {
        final int VALID_RESPONSE_CODE = 200;
        String inLine = "";
        URL url = new URL(Web.COLLEGE_SCORECARD_QUERY);
        HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        int responseCode = c.getResponseCode();
        Log.println(Log.ASSERT, "0", "Response Code: " + responseCode);
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
        Log.println(Log.ASSERT, "0", college.toString());
    }
}
