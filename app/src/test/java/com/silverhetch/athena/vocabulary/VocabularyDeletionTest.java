package com.silverhetch.athena.vocabulary;

import com.silverhetch.athena.MainActivity;
import com.silverhetch.athena.database.DatabaseFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mikes on 12/14/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class VocabularyDeletionTest {
    private DatabaseFactory databaseFactory;

    @Before
    public void setUp() throws Exception {
        databaseFactory = new DatabaseFactory(Robolectric.setupActivity(MainActivity.class));
    }

    @Test
    public void delete_checkCount() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        Vocabulary vocabulary = vocabularies.add("word", "note");
        new VocabularyDeletion(databaseFactory,vocabulary.id()).delete();
        Assert.assertEquals(0, vocabularies.all().length);
    }
}