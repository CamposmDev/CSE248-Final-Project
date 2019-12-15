package com.campos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "CollegeNavigatorDB";
    private static final String TABLE_COLLEGE = "College";
    private static final String TABLE_USERACCOUNT = "UserAccount";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
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
}
