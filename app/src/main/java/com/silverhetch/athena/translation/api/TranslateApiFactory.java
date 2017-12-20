package com.silverhetch.athena.translation.api;

import com.silverhetch.clotho.connection.HttpRequest;

/**
 * Created by mikes on 12/17/2017.
 */

public class TranslateApiFactory {
    public HttpRequest api(String keyword) {
        return new AzureTranslatorAPI(keyword);
    }
}
