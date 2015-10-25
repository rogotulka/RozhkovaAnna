package org.rogotulka.rozhkovaanna;


import android.test.AndroidTestCase;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;
import org.rogotulka.rozhkovaanna.server.api.ApiClient;
import org.rogotulka.rozhkovaanna.server.api.ApiClientProvider;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.io.IOException;
import java.util.List;


public class ApiClientImplTest extends AndroidTestCase {

    public void testApiClientRequestRss() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        List<News> news = apiClient.execute(new RssRequest(Source.GAZETA));
        assertNotNull(news);
    }

    public void testNullRequestRssApiClient() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        List<News> news = apiClient.execute(new RssRequest(null));
        assertNotNull(news);
    }

    public void testNewsRequestRssApiClient() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        List<News> news = apiClient.execute(new RssRequest(Source.LENTA));
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
