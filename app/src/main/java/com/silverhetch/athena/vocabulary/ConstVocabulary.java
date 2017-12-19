package com.silverhetch.athena.vocabulary;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/13/2017.
 */

class ConstVocabulary implements Vocabulary {
    private final DatabaseFactory databaseFactory;
    private final long id;
    private final String value;
    private final String translation;

    ConstVocabulary(DatabaseFactory databaseFactory, long id, String value, String translation) {
        this.databaseFactory = databaseFactory;
        this.id = id;
        this.value = value;
        this.translation = translation;
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
    public String translation() {
        return translation;
    }

    @Override
    public Vocabulary updateTranslation(String translation) {
        return new VocabularyTranslationUpdate(databaseFactory, this).update(translation);
    }

    @Override
    public void delete() {
        new VocabularyDeletion(databaseFactory, id).delete();
    }
}
