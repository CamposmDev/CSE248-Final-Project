package com.campos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.io.Serializable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelper;
    private static final String DB_NAME = "CollegeNavigatorDB";
    private static final String TABLE_COLLEGE = "College";
    private static final String TABLE_USERACCOUNT = "UserAccount";

    public static DatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        dbHelper = this;
        onUpgrade(dbHelper.getWritableDatabase(), 1, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.println(Log.ASSERT, "0", "Creating " + TABLE_USERACCOUNT + " table");
        db.execSQL("CREATE TABLE " + TABLE_USERACCOUNT + "(" +
                "firstName VARCHAR(64), " +
                "lastName VARCHAR(64), " +
                "email VARCHAR(256), " +
                "readingScore int, " +
                "mathScore int, " +
                "writingScore int, " +
                "username VARCHAR(64) PRIMARY KEY, " +
                "password VARCHAR(16))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERACCOUNT);
        onCreate(db);
    }

    public Cursor findCollege(String query) {
        return null;
    }

    public boolean addCollege() {
        return false;
    }

    public Cursor findUserAccount(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USERACCOUNT + " where username=\"" + username + "\" ", null);
        return res;
    }

    public boolean addUserAccount(String firstName, String lastName, String email, int readingScore, int mathScore, int writingScore, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("readingScore", readingScore);
        contentValues.put("mathScore", mathScore);
        contentValues.put("writingScore", writingScore);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert(TABLE_USERACCOUNT, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
