package com.silverhetch.athena.vocabulary;

import android.content.Context;

import com.silverhetch.athena.database.DatabaseFactory;

/**
 * Created by mikes on 12/14/2017.
 */

public class VocabulariesFactory {
    private final Context context;

    public VocabulariesFactory(Context context) {
        this.context = context;
    }

    public Vocabularies vocabularies() {
        return new DatabaseVocabularies(new DatabaseFactory(context));
    }
}
