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
                "CREATE TABLE Group (" +
                "_id INTEGER PRIMARY KEY NOT NULL UNIQUE" +
                ")"
        );
        db.execSQL(
                "CREATE TABLE Period (" +
                "_id INTEGER PRIMARY KEY NOT NULL UNIQUE" +
                ")"
        );
        db.execSQL(
                "CREATE TABLE State (" +
                "_id INTEGER PRIMARY KEY NOT NULL UNIQUE" +
                ")"
        );
        db.execSQL(
                "CREATE TABLE Type (" +
                "_id INTEGER PRIMARY KEY NOT NULL UNIQUE" +
                ")"
        );
        db.execSQL(
            "CREATE TABLE Element (" +
            "_id INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
            "name TEXT, " +
            "symbol TEXT, " +
            "weight REAL," +
            "groupID INTEGER," +
            "periodID INTEGER," +
            "stateID INTEGER," +
            "typeID INTEGER," +
            "FOREIGN KEY(groupID) REFERENCES Group(_id)" +
            "FOREIGN KEY(periodID) REFERENCES Period(_id)" +
            "FOREIGN KEY(stateID) REFERENCES State(_id)" +
            "FOREIGN KEY(typeID) REFERENCES Type(_id)" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
