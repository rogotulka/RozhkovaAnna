package org.rogotulka.rozhkovaanna;


import android.test.AndroidTestCase;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.server.api.ApiClientImpl;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.io.IOException;
import java.util.List;


public class ApiClientImplTest extends AndroidTestCase {

    public void testApiClientRequestRss() throws IOException, InterruptedException {
        ApiClientImpl apiClient = new ApiClientImpl();
        List<News> news = apiClient.execute(new RssRequest("http://www.gazeta.ru/export/rss/lenta.xml"));
        assertNotNull(news);
    }

    public void testEmptyRequestRssApiClient() throws IOException, InterruptedException {
        ApiClientImpl apiClient = new ApiClientImpl();
        List<News> news = apiClient.execute(new RssRequest(""));
        assertNotNull(news);
    }

    public void testNullRequestRssApiClient() throws IOException, InterruptedException {
        ApiClientImpl apiClient = new ApiClientImpl();
        List<News> news = apiClient.execute(new RssRequest(null));
        assertNotNull(news);
    }

    public void testNewsRequestRssApiClient() throws IOException, InterruptedException {
        ApiClientImpl apiClient = new ApiClientImpl();
        List<News> news = apiClient.execute(new RssRequest("http://lenta.ru/rss"));
        assertNotSame(news.size(), 0);
        for (News newsItem : news) {
            assertNotNull(newsItem.getTitle());
            assertNotNull(newsItem.getDescription());
            assertNotNull(newsItem.getDate());
            assertNotNull(newsItem.getImage());
        }
        assertNotNull(news);
    }

}
