package com.silverhetch.athena.speech;

import android.speech.tts.TextToSpeech;

/**
 * Created by mikes on 1/6/2018.
 */

public interface Speech {
    void initial();
    void release();
    void speak(String value);
}
