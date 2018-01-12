package com.silverhetch.athena.dictionary;

import android.content.Context;

/**
 * Created by mikes on 1/12/2018.
 */

public class DictionaryFactory {
    private final Context context;

    public DictionaryFactory(Context context) {
        this.context = context;
    }

    public Dictionary dictionary() {
        return new CambridgeDictionary(context);
    }
}
