package org.rogotulka.rozhkovaanna.data;


public enum Source {

    LENTA("http://lenta.ru/rss"),
    GAZETA("http://www.gazeta.ru/export/rss/lenta.xml");

    private String source;

    Source(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
