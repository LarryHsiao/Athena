package com.silverhetch.athena.speech;

import android.content.Context;

/**
 * Created by mikes on 1/6/2018.
 */

public class SpeechFactory {
    public Speech speech(Context context) {
        return new AndroidSpeech(context);
    }
}
