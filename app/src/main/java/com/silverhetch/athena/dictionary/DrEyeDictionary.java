package com.silverhetch.athena.dictionary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static android.content.Intent.ACTION_VIEW;

/**
 * Created by mikes on 1/12/2018.
 */

class DrEyeDictionary implements Dictionary {
    private static final String URL = "https://yun.dreye.com/dict_new/dict_min.php";
    private final Context context;

    DrEyeDictionary(Context context) {
        this.context = context;
    }

    @Override
    public void search(String word) throws Exception {
        Uri uri = Uri.parse(URL).buildUpon().appendQueryParameter("w", word).build();
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
