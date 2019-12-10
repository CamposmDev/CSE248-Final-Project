package com.campos.util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

        }
        return null;
    }

    public static void buildCollegeNavigatorDB() throws SQLException {
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

    }

    public static void fillDBWithData() {

    }
}
