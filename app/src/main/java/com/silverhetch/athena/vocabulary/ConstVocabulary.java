package com.silverhetch.athena.vocabulary;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/13/2017.
 */

public class ConstVocabulary implements Vocabulary {
    private final DatabaseFactory databaseFactory;
    private final long id;
    private final String value;
    private final String note;

    public ConstVocabulary(DatabaseFactory databaseFactory, long id, String value, String note) {
        this.databaseFactory = databaseFactory;
        this.id = id;
        this.value = value;
        this.note = note;
    }


    @Override
    public long id() {
        return id;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String note() {
        return note;
    }

    @Override
    public void delete() {

    }
}
