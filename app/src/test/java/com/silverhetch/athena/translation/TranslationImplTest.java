package com.silverhetch.athena.translation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by mikes on 12/17/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class TranslationImplTest {
    @Test
    public void translate() throws Exception {
        String result = new TranslationImpl().translate("apple");
        assertEquals("蘋果", result);
    }
}