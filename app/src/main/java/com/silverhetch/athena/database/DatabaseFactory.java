package com.silverhetch.athena.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mikes on 12/13/2017.
 */

public class DatabaseFactory {
    private final Context context;

    public DatabaseFactory(Context context) {
        this.context = context;
    }

    public SQLiteDatabase database() {
        return new AthenaSQLite(context).getWritableDatabase();
    }

}
