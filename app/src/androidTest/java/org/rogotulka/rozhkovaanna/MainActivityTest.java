package org.rogotulka.rozhkovaanna;

import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists(){
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }
}
