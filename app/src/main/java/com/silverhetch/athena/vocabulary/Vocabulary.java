package com.silverhetch.athena.vocabulary;

/**
 * Created by mikes on 12/13/2017.
 */

public interface Vocabulary {
    long id();
    String value();
    String note();
    void delete();
}
