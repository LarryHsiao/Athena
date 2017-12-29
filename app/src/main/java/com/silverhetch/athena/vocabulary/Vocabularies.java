package com.silverhetch.athena.vocabulary;

/**
 * Created by mikes on 12/13/2017.
 */

public interface Vocabularies {
    Vocabulary add(String vocabulary);

    Vocabulary[] all();

    Vocabulary[] search(String keyword);

    Vocabulary byId(long id);

    void clear();
}
