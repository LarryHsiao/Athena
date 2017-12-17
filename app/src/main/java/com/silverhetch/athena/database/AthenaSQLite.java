package com.silverhetch.athena.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mikes on 12/13/2017.
 */

class AthenaSQLite extends SQLiteOpenHelper {

    AthenaSQLite(Context context) {
        super(context, "athena.db", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS vocabulary ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "value TEXT NOT NULL " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vocabulary");
        onCreate(db);
    }
}
