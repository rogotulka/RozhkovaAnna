package org.rogotulka.rozhkovaanna;

import android.test.AndroidTestCase;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;
import org.rogotulka.rozhkovaanna.logic.Logic;
import org.rogotulka.rozhkovaanna.logic.LogicProvider;

import java.util.ArrayList;
import java.util.List;

public class LogicImplTest extends AndroidTestCase {

    public void testCompoundNews() {
        Logic logic = LogicProvider.getLogic();
        List<Source> sourceList = new ArrayList<>();
        sourceList.add(Source.GAZETA);
        sourceList.add(Source.LENTA);
        List<News> news = logic.getCompoundNews(sourceList);
        assertNotNull(news);
        assertNotSame(0, news.size());
    }
}
