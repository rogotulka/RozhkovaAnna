package org.rogotulka.rozhkovaanna;


import android.test.AndroidTestCase;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.server.api.ApiClientImpl;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.io.IOException;
import java.util.List;


public class ApiClientImplTest extends AndroidTestCase {

    public void testApiClientRequestRss() {

        List<News> news = null;

        ApiClientImpl apiClient = new ApiClientImpl();

        try {
            news = apiClient.execute(new RssRequest("http://www.gazeta.ru/export/rss/lenta.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

        assertNotNull(news);

    }

}
