package org.rogotulka.rozhkovaanna.logic;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;

import java.util.List;

public interface Logic {

    List<News> getCompoundNews(List<Source> sources);

}
