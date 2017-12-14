package com.silverhetch.athena.vocabulary;

import android.content.Context;

import com.silverhetch.athena.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mikes on 12/14/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class VocabulariesFactoryTest {
    @Test
    public void newVocabularies() throws Exception {
        Context context = Robolectric.setupActivity(MainActivity.class);
        new VocabulariesFactory(context).vocabularies();
    }
}