package com.silverhetch.athena.vocabulary;

import android.content.ContentValues;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/13/2017.
 */

class ContentValuesVocabulary implements Vocabulary {
    private final DatabaseFactory databaseFactory;
    private final long id;
    private final ContentValues values;

    ContentValuesVocabulary(DatabaseFactory databaseFactory, long id, ContentValues values) {
        this.databaseFactory = databaseFactory;
        this.id = id;
        this.values = values;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public String value() {
        return values.getAsString("value");
    }

    @Override
    public String note() {
        return values.getAsString("note");
    }

    @Override
    public void delete() {
        new VocabularyDeletion(databaseFactory, id).delete();
    }
}
