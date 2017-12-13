package com.silverhetch.athena.database;

import com.silverhetch.athena.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mikes on 12/13/2017.
 */
@RunWith(RobolectricTestRunner.class)
public class DatabaseFactoryTest {
    @Test
    public void initialize_success() throws Exception {
        new DatabaseFactory(Robolectric.setupActivity(MainActivity.class)).database();
    }
}