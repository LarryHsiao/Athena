package com.silverhetch.athena.vocabulary;

/**
 * Created by mikes on 12/13/2017.
 */

public interface Vocabularies {
    Vocabulary add(String vocabulary, String note);

    Vocabulary[] all();

    void clear();
}
