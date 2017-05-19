package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Prakash-hp on 19-05-2017.
 */

public class ITGDbHelper extends SQLiteOpenHelper {

    private static final String LOGGER_NAME = "ITGDbHelper" ;

    // Table Name
    public static final String TABLE_NAME = "XCONFIGURATIONS";

    // Table columns
    public static final String _ID = "_id";
    public static final String KEY_NAME = "keyName";
    public static final String KEY_VALUE = "keyValue";

    // Database Information
    static final String DB_NAME = "GWPM_LOCAL.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, " + KEY_VALUE + " TEXT);";

    public ITGDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOGGER_NAME, "onUpgrade Called") ;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}