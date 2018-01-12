package com.silverhetch.athena.dictionary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static android.content.Intent.ACTION_VIEW;

/**
 * Created by mikes on 1/12/2018.
 */

class CambridgeDictionary implements Dictionary {
    private static final String URL = "https://dictionary.cambridge.org/zht/%E6%90%9C%E7%B4%A2/english-chinese-traditional/direct/";
    private final Context context;

    CambridgeDictionary(Context context) {
        this.context = context;
    }

    @Override
    public void search(String word) throws Exception {
        Uri uri = Uri.parse(URL).buildUpon().appendQueryParameter("q", word).build();
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
