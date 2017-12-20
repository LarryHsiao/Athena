package com.silverhetch.athena.translation;

import android.util.Xml;

import com.silverhetch.athena.translation.api.TranslateApiFactory;
import com.silverhetch.clotho.connection.HttpRequest;
import com.silverhetch.clotho.connection.HttpResponse;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikes on 12/17/2017.
 */

public class TranslationImpl implements Translation {

    @Override
    public String translate(String input)throws Exception {
            HttpRequest get = new TranslateApiFactory().api(input);
            HttpResponse response = get.request();
            if (response.code != 200) {
                throw new RuntimeException("Response code not 200");
            }

            String responseXML = new String(response.bodyBytes);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(responseXML));
            List<String> results = new ArrayList<>();
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.TEXT) {
                    results.add(parser.getText());
                }
                eventType = parser.next();
            }
            return results.size() > 0 ? results.get(0) : "";
    }
}
