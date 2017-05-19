package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Prakash-hp on 19-05-2017.
 */

public class ITGDbManager {

    private static final String LOGGER_NAME = "ITGDbManager" ;

    private ITGDbHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public ITGDbManager(Context c) {
        context = c;
    }

    public ITGDbManager open() throws SQLException {
        dbHelper = new ITGDbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Long insert(String keyName, String keyValue) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ITGDbHelper.KEY_NAME, keyName);
        contentValue.put(ITGDbHelper.KEY_VALUE, keyValue);
        return database.insert(ITGDbHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{ITGDbHelper._ID, ITGDbHelper.KEY_NAME, ITGDbHelper.KEY_VALUE};
        Cursor cursor = database.query(ITGDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String fetchByKeyName(String keyName) {
        String keyValue = null ;
        String[] columns = new String[]{ITGDbHelper._ID, ITGDbHelper.KEY_NAME, ITGDbHelper.KEY_VALUE};
        Cursor cursor = database.query(ITGDbHelper.TABLE_NAME, columns, ITGDbHelper.KEY_NAME + " = ? ", new String[]{keyName}, null, null, null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                keyValue =  cursor.getString(cursor.getColumnIndex(ITGDbHelper.KEY_VALUE));
            }
        }
        Log.d(LOGGER_NAME, "KeyName: " + keyName + " - " + " KeyValue: " + keyValue ) ;
        return keyValue;
    }

    public int update(long _id, String keyName, String keyValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITGDbHelper.KEY_NAME, keyName);
        contentValues.put(ITGDbHelper.KEY_VALUE, keyValue);
        int i = database.update(ITGDbHelper.TABLE_NAME, contentValues, ITGDbHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(ITGDbHelper.TABLE_NAME, ITGDbHelper._ID + "=" + _id, null);
    }
}
