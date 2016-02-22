package com.example.aglubatj.chemistryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 2/18/2016.
 */
public class PeriodicTableHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "pTableData";
    private static final int DB_Version = 1;

    public PeriodicTableHelper(Context context){
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE Element (" +
            "_id INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "symbol TEXT, " +
            "weight REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
