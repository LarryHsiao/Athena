package com.silverhetch.athena.vocabulary;

import android.content.Context;

import com.silverhetch.athena.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by mikes on 12/14/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class ConstVocabularyTest {
    @Before
    public void setUp() throws Exception {
        final Context context = Robolectric.setupActivity(MainActivity.class);
        Vocabularies vocabularies = new VocabulariesFactory(context).vocabularies();
        vocabularies.clear();
    }

    @Test
    public void delete() throws Exception {
        final Context context = Robolectric.setupActivity(MainActivity.class);
        Vocabularies vocabularies = new VocabulariesFactory(context).vocabularies();
        vocabularies.add("abc");
        vocabularies.all()[0].delete();
        assertEquals(0, vocabularies.all().length);
    }

    @Test
    public void updateTranslation() throws Exception {
        final Context context = Robolectric.setupActivity(MainActivity.class);
        Vocabularies vocabularies = new VocabulariesFactory(context).vocabularies();
        Vocabulary vocabulary = vocabularies.add("abc");
        Vocabulary newVocabulary = vocabulary.updateTranslation("newTranslation");
        assertEquals("",vocabulary.translation());
        assertEquals("newTranslation", newVocabulary.translation());
    }
}