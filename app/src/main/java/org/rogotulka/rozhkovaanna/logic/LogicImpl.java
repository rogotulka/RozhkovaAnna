package org.rogotulka.rozhkovaanna.logic;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;
import org.rogotulka.rozhkovaanna.server.api.ApiClient;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LogicImpl implements Logic {

    private ApiClient mApiClient;
    private Executor mExecutor;

    LogicImpl(ApiClient apiClient) {
        mApiClient = apiClient;
        int nThreads = Runtime.getRuntime().availableProcessors() + 1;
        mExecutor = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public List<News> getCompoundNews(List<Source> sources) {
        List<News> newsList = new ArrayList<>();

        if (sources == null) {
            return newsList;
        }

        CountDownLatch countDownLatch = new CountDownLatch(sources.size());
        List<RssRequestRunnable> tasks = new ArrayList<>();
        for (Source source : sources) {
            RssRequestRunnable rssRequestRunnable = new RssRequestRunnable(
                    mApiClient,
                    new RssRequest(source),
                    countDownLatch);
            tasks.add(rssRequestRunnable);
            mExecutor.execute(rssRequestRunnable);
        }

        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //NOP
        }

        for (RssRequestRunnable rssRequestRunnable : tasks) {
            newsList.addAll(rssRequestRunnable.getNewsList());
        }

        Collections.sort(newsList);

        return newsList;
    }

}
