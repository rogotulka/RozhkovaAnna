package org.rogotulka.rozhkovaanna.server.api.request;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;

import java.util.List;

public class RssRequest implements Request<List<News>> {

    private Source source;

    public RssRequest(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RssRequest that = (RssRequest) o;

        return !(source != null ? !source.equals(that.source) : that.source != null);

    }

    @Override
    public int hashCode() {
        return source != null ? source.hashCode() : 0;
    }
}
