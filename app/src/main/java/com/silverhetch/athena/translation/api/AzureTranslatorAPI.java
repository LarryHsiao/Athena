package com.silverhetch.athena.translation.api;

import com.silverhetch.clotho.connection.HttpGet;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by mikes on 12/17/2017.
 */

class AzureTranslatorAPI extends HttpGet {
    AzureTranslatorAPI(String keyWord) {
        super("https://api.microsofttranslator.com/V2/Http.svc/Translate?to=zh-TW&text=" + URLEncoder.encode(keyWord));
    }

    @Override
    protected void setupConnection(HttpURLConnection connection) throws Exception {
        super.setupConnection(connection);
        connection.addRequestProperty("Ocp-Apim-Subscription-Key", "c526ec48a9a74b688f044e06ba45971c");
    }
}
