package org.rogotulka.rozhkovaanna.logic;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.server.api.ApiClient;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class RssRequestRunnable implements Runnable {

    private ApiClient mApiClient;
    private RssRequest mRssRequest;
    private CountDownLatch mCountDownLatch;
    private List<News> mNewsList;

    RssRequestRunnable(ApiClient apiClient, RssRequest rssRequest, CountDownLatch countDownLatch) {
        mApiClient = apiClient;
        mRssRequest = rssRequest;
        mCountDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            mNewsList = mApiClient.execute(mRssRequest);
        } catch (IOException e) {
            //NOP
        }

        mCountDownLatch.countDown();
    }

    List<News> getNewsList() {
        return mNewsList;
    }
}
