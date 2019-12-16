package com.campos.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {
    private static MyDB dbHelper;
    private static final String DB_NAME = "CollegeNavigatorDB";
    private static final String TABLE_COLLEGE = "College";
    private static final String TABLE_USER_ACCOUNT = "UserAccount";

    public static MyDB getDb() {
        return dbHelper;
    }

    public static void initMyDB(Context context) {
        dbHelper = new MyDB(context);
    }

    private MyDB(Context context) {
        super(context, DB_NAME, null, 1);
        dbHelper = this;
        if (MyUtils.isNewYear()) {
            onUpgrade(dbHelper.getWritableDatabase(), 1, 1);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Sysout.println("Creating " + TABLE_USER_ACCOUNT + " table");
        db.execSQL("CREATE TABLE " + TABLE_USER_ACCOUNT + "(" +
                "firstName VARCHAR(64), " +
                "lastName VARCHAR(64), " +
                "email VARCHAR(256), " +
                "readingScore int, " +
                "mathScore int, " +
                "writingScore int, " +
                "username VARCHAR(64) PRIMARY KEY, " +
                "password VARCHAR(16))");

        Sysout.println("Creating " + TABLE_COLLEGE + " table");
        db.execSQL("CREATE TABLE College(" +
                "id VARCHAR PRIMARY KEY, " +
                "name VARCHAR, " +
                "regionId int, " +
                "zip VARCHAR, " +
                "city VARCHAR, " +
                "state VARCHAR(4), " +
                "url VARCHAR, " +
                "latestStudentSize int, " +
                "tuitionInState int, " +
                "tuitionOutOfState int, " +
                "admissionsRate decimal, " +
                "degreesAwarded int, " +
                "readingScore25th int, " +
                "readingScore75th int, " +
                "mathScore25th int, " +
                "mathScore75th int, " +
                "writingScore25th int, " +
                "writingScore75th int, " +
                "menOnly int, " +
                "womenOnly int)");
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DBLoader().execute();
            }
        }).start();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLLEGE);
        onCreate(db);
    }

    public Cursor findCollegeByName(String name) {
        String query = "SELECT * FROM " + TABLE_COLLEGE + " WHERE name=\'" + name + "\'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public Cursor findCollegeByConditions(String conditions) {
        String query = "SELECT name FROM " + TABLE_COLLEGE + " WHERE " + conditions;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public boolean addCollege(String id, String name, int regionId, String zip, String city,
                              String state, String url, int latestStudentSize, int tuitionInState, int tuitionOutOfState,
                              double admissionsRate, int degreesAwarded, int readingScore25th, int readingScore75th,
                              int mathScore25th, int mathScore75th, int writingScore25th, int writingScore75th, int menOnly, int womenOnly) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("regionId", regionId);
        contentValues.put("zip", zip);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("url", url);
        contentValues.put("latestStudentSize", latestStudentSize);
        contentValues.put("tuitionInState", tuitionInState);
        contentValues.put("tuitionOutOfState", tuitionOutOfState);
        contentValues.put("admissionsRate", admissionsRate);
        contentValues.put("degreesAwarded", degreesAwarded);
        contentValues.put("readingScore25th", readingScore25th);
        contentValues.put("readingScore75th", readingScore75th);
        contentValues.put("mathScore25th", mathScore25th);
        contentValues.put("mathScore75th", mathScore75th);
        contentValues.put("writingScore25th", writingScore25th);
        contentValues.put("writingScore75th", writingScore75th);
        contentValues.put("menOnly", menOnly);
        contentValues.put("womenOnly", womenOnly);
        long result = db.insert(TABLE_COLLEGE, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public boolean updateUserAccountByUsername(
            String username, String firstName, String lastName, String email,
            int readingScore, int mathScore, int writingScore) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("readingScore", readingScore);
        contentValues.put("mathScore", mathScore);
        contentValues.put("writingScore", writingScore);
        contentValues.put("username", username);
//        contentValues.put("password", password);
        db.update(TABLE_USER_ACCOUNT, contentValues, "username = ?", new String[] { username });
        return true;
    }

    public int deleteUserAccountByUsername(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_USER_ACCOUNT, "username = ?", new String[] {username});
    }

    public Cursor findUserAccountByUsername(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER_ACCOUNT + " where username=\"" + username + "\" ", null);
        return res;
    }

    public boolean addUserAccount(String firstName, String lastName, String email, int readingScore, int mathScore, int writingScore, String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("readingScore", readingScore);
        contentValues.put("mathScore", mathScore);
        contentValues.put("writingScore", writingScore);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert(TABLE_USER_ACCOUNT, null, contentValues);
        if (result == -1)
            return false;

        return true;
    }
}
