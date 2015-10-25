package org.rogotulka.rozhkovaanna;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists(){
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }

    public void testListViewExists(){
        MainActivity mainActivity = getActivity();
        ListView vListView = (ListView) mainActivity.findViewById(R.id.news_list);
        assertNotNull(vListView);
    }
}
