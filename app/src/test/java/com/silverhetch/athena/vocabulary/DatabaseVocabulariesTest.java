package com.silverhetch.athena.vocabulary;

import android.content.Context;

import com.silverhetch.athena.MainActivity;
import com.silverhetch.athena.database.DatabaseFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by mikes on 12/13/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class DatabaseVocabulariesTest {
    private DatabaseFactory databaseFactory;

    @Before
    public void setUp() throws Exception {
        databaseFactory = new DatabaseFactory(Robolectric.setupActivity(MainActivity.class));
    }

    @Test
    public void insert_checkContent() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        Vocabulary insertedVocabulary = vocabularies.add("word");
        assertEquals(1, insertedVocabulary.id());
        assertEquals("word", insertedVocabulary.value());
    }

    @Test
    public void query_checkContent() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        vocabularies.add("word");
        Vocabulary insertedVocabulary = vocabularies.all()[0];
        assertEquals(1, insertedVocabulary.id());
        assertEquals("word", insertedVocabulary.value());
        assertEquals("", insertedVocabulary.translation());
    }

    @Test
    public void search_checkCount() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        vocabularies.add("word");
        vocabularies.add("word2");
        assertEquals(2, vocabularies.search("word").length);
    }

    @Test
    public void byId_checkContent() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        vocabularies.add("word");
        Vocabulary insertedVocabulary = vocabularies.byId(1);
        assertEquals(1, insertedVocabulary.id());
        assertEquals("word", insertedVocabulary.value());
        assertEquals("", insertedVocabulary.translation());
    }

    @Test
    public void insert_checkCount() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        vocabularies.add("word");
        assertEquals(1, vocabularies.all().length);
    }

    @Test
    public void insert_invalidedValues() throws Exception {
        try {
            DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
            vocabularies.add(null);
            fail();
        } catch (Exception ignore) {
            assertTrue(true);
        }
    }

    @Test
    public void delete_checkCount() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        Vocabulary vocabulary = vocabularies.add("word");
        vocabulary.delete();
        assertEquals(0, vocabularies.all().length);
    }

    @Test
    public void clear() throws Exception {
        DatabaseVocabularies vocabularies = new DatabaseVocabularies(databaseFactory);
        Vocabulary vocabulary = vocabularies.add("123");
        vocabulary.delete();
        assertEquals(0, vocabularies.all().length);
    }

    @Test
    public void updateTranslation() throws Exception {
        final Context context = Robolectric.setupActivity(MainActivity.class);
        Vocabularies vocabularies = new VocabulariesFactory(context).vocabularies();
        Vocabulary vocabulary = vocabularies.add("abc");
        vocabulary.updateTranslation("newTranslation");
        org.junit.Assert.assertEquals("", vocabulary.translation());
        org.junit.Assert.assertEquals("newTranslation", vocabularies.all()[0].translation());
    }
}