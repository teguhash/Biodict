package com.hift.biodict;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TeguhAS on 25-Jul-17.
 */

public class DBObject {
    private static DictionaryDatabase dbHelper;
    private SQLiteDatabase db;

    public DBObject(Context context) {
        dbHelper = new DictionaryDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection() {
        return this.db;
    }

    public void closeDbConnection() {
        if (this.db != null) {
            this.db.close();
        }
    }
}
