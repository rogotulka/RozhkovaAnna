package org.rogotulka.rozhkovaanna.data;


public enum Source {

    LENTA("http://lenta.ru/rss", "lenta.ru"),
    GAZETA("http://www.gazeta.ru/export/rss/lenta.xml", "gazeta.ru");

//TODO: я бы переименовал в url
    private String source;
    private String name;

    Source(String source, String name) {
        this.source = source;
        this.name = name;
    }
//TODO: я бы переименовал в getUrl
    public String getSourceString() {
        return source;
    }

    public String getName() {
        return name;
    }
}
