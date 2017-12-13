package com.silverhetch.athena.vocabulary;

import com.silverhetch.athena.MainActivity;
import com.silverhetch.athena.database.DatabaseFactory;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mikes on 12/13/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class DatabaseVocabulariesTest {
    private final DatabaseFactory databaseFactory = new DatabaseFactory(Robolectric.setupActivity(MainActivity.class));

    @Test
    public void insert_checkContent() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        vocabularies.add("word", "note" );
        Vocabulary insertedVocabulary = vocabularies.all()[0];
        Assert.assertEquals("word", insertedVocabulary.value());
        Assert.assertEquals("note", insertedVocabulary.note());
    }
}