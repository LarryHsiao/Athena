package com.silverhetch.athena.vocabulary;

import android.database.sqlite.SQLiteDatabase;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/14/2017.
 */

class VocabularyDeletion {
    private final DatabaseFactory databaseFactory;
    private final long id;

    VocabularyDeletion(DatabaseFactory databaseFactory, long id) {
        this.databaseFactory = databaseFactory;
        this.id = id;
    }

    void delete() {
        try (SQLiteDatabase database = databaseFactory.database()) {
            final int rows = database.delete("vocabulary", "id=?", new String[]{String.valueOf(id)});
            if (rows != 1) {
                throw new RuntimeException("Vocabulary deletion failed.");
            }
        }
    }
}
