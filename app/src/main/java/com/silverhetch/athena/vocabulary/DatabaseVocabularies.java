package com.silverhetch.athena.vocabulary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.silverhetch.athena.database.DatabaseFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikes on 12/13/2017.
 */

class DatabaseVocabularies implements Vocabularies {
    private final DatabaseFactory databaseFactory;

    DatabaseVocabularies(DatabaseFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }

    @Override
    public Vocabulary add(String value, String note) {
        try (SQLiteDatabase database = databaseFactory.database()) {
            ContentValues insertValues = new ContentValues();
            insertValues.put("value", value);
            insertValues.put("note", note);
            final long insertedId = database.insert("vocabulary", null, insertValues);
            if (-1 == insertedId) {
                throw new RuntimeException("insertion failed" );
            }
            return new ContentValuesVocabulary(databaseFactory, insertedId, insertValues);
        }
    }

    @Override
    public Vocabulary[] all() {
        try (SQLiteDatabase database = databaseFactory.database();
             Cursor cursor = database.query("vocabulary", null, null, null, null, null, null);
        ) {
            List<Vocabulary> result = new ArrayList<>();
            while (cursor.moveToNext()) {
                result.add(new ConstVocabulary(databaseFactory,
                        cursor.getLong(cursor.getColumnIndex("id" )),
                        cursor.getString(cursor.getColumnIndex("value" )),
                        cursor.getString(cursor.getColumnIndex("note" ))));
            }
            return result.toArray(new Vocabulary[result.size()]);
        }
    }
}
