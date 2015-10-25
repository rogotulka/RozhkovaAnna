package org.rogotulka.rozhkovaanna.server.api;

import android.util.Xml;

import org.rogotulka.rozhkovaanna.data.News;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class XMLParser {

    private static final String XML_RSS = "rss";
    private static final String ns = null;

    private static final String XML_CHANNEL = "channel";
    private static final String XML_ITEM = "item";
    private static final String XML_TITLE = "title";
    private static final String XML_DESCRIPTION = "description";
    private static final String XML_DATE = "pubDate";

    public List<News> parseNews(InputStream in) {
        XmlPullParser parser = startParse(in);
        if (parser != null) {
            return extractNewsList(parser);
        }
        return null;
    }

    private List<News> extractNewsList(XmlPullParser parser) {
        List<News> result = new ArrayList<>();
        try {
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, ns, XML_CHANNEL);
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals(XML_ITEM)) {
                    News news = extractNews(parser);
                    result.add(news);
                } else {
                    skip(parser);
                }
            }
        } catch (XmlPullParserException e) {
            // TODO
        } catch (IOException e) {
            // TODO
        }
        return result;
    }

    private News extractNews(XmlPullParser parser) {
        News news = new News();
        try {
            parser.require(XmlPullParser.START_TAG, ns, XML_ITEM);
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals(XML_TITLE)) {
                    news.setTitle(readTag(parser, XML_TITLE));
                } else if (name.equals(XML_DESCRIPTION)) {
                    news.setDescription(readTag(parser, XML_DESCRIPTION));
                } else {
                    skip(parser);
                }
            }
        } catch (XmlPullParserException e) {
            // TODO
        } catch (IOException e) {
            // TODO
        }
        return news;
    }

    private XmlPullParser startParse(InputStream in) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_DOCDECL, false);
            parser.setInput(in, null);
            parser.defineEntityReplacementText("&nbsp;", "\u00A0");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, ns, XML_RSS);
            return parser;
        } catch (XmlPullParserException e) {
            // TODO
        } catch (IOException e) {
            // TODO
        }
        return null;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return title;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
