package com.silverhetch.athena.vocabulary;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/20/2017.
 */

public class VocabularyTranslationUpdate {
    private final DatabaseFactory databaseFactory;
    private final Vocabulary vocabulary;

    public VocabularyTranslationUpdate(DatabaseFactory databaseFactory, Vocabulary vocabulary) {
        this.databaseFactory = databaseFactory;
        this.vocabulary = vocabulary;
    }

    public Vocabulary update(String translation){
        try (SQLiteDatabase database = databaseFactory.database()) {
            ContentValues values = new ContentValues();
            values.put("translation", translation);
            database.update("vocabulary", values, "id=?", new String[]{String.valueOf(vocabulary.id())});
            return new ConstVocabulary(databaseFactory, vocabulary.id(), vocabulary.value(), translation);
        }
    }
}
