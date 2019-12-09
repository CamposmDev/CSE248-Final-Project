package com.campos.util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyDB {
    private static final String CONNECT_TO_DB = "jdbc:sqlite:CollegeNavigatorDB.sqlite";

    public static void buildCollegeNavigatorDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(CONNECT_TO_DB);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop table if exists College");
            conn.close();
        } catch (Exception e) {
            Log.println(Log.ASSERT, "", e.getMessage());
        }

    }
}
