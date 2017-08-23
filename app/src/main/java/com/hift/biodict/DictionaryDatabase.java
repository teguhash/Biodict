package com.hift.biodict;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by TeguhAS on 25-Jul-17.
 */

public class DictionaryDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "dict.db";
    private static final int DATABASE_VERSION = 1;

    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }
}
