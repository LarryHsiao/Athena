package com.silverhetch.athena.ui.vocabularylist;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.silverhetch.athena.translation.TranslationImpl;
import com.silverhetch.athena.vocabulary.VocabulariesFactory;
import com.silverhetch.athena.vocabulary.Vocabulary;

/**
 * Created by mikes on 12/30/2017.
 */

class TranslationLoader extends AsyncTaskLoader<Vocabulary> {
    private final long id;

    TranslationLoader(Context context, long id) {
        super(context);
        this.id = id;
    }

    @Override
    public Vocabulary loadInBackground() {
        try {
            Vocabulary vocabulary = new VocabulariesFactory(getContext()).vocabularies().byId(id);
            String translation = new TranslationImpl().translate(vocabulary.value());
            if (vocabulary.value().equals(translation)){
                throw new RuntimeException("no translation");
            }
            return vocabulary.updateTranslation(translation);
        } catch (Exception ignore) {
            return null;
        }
    }
}
