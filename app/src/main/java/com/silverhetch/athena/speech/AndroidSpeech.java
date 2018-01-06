package com.silverhetch.athena.speech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.QUEUE_FLUSH;
import static android.speech.tts.TextToSpeech.SUCCESS;

/**
 * Created by mikes on 1/6/2018.
 */

class AndroidSpeech implements Speech {
    private final Context context;
    private TextToSpeech textToSpeech;
    private int speechStatus;

    AndroidSpeech(Context context) {
        this.context = context;
    }

    @Override
    public void initial() {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                speechStatus = status;
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
    }

    @Override
    public void speak(String value) {
        if (speechStatus != SUCCESS) {
            return;
        }
        textToSpeech.speak(value, QUEUE_FLUSH, null, String.valueOf(System.currentTimeMillis()));
    }
}
