package com.silverhetch.athena;

import android.net.Uri;

import com.silverhetch.athena.vocabulary.Vocabulary;

import java.util.Locale;

/**
 * Created by mikes on 12/17/2017.
 */

public class GoogleTranslateUrl {
    private final Vocabulary vocabulary;


    public GoogleTranslateUrl(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public Uri uri() {
        Uri.Builder builder = Uri.parse("https://translate.google.com/").buildUpon();
        builder.appendQueryParameter("sl","auto#auto/zh-TW/apple");
        return builder.build();
    }
}
