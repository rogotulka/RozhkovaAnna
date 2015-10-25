package org.rogotulka.rozhkovaanna.server.api;

import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.server.api.request.Request;
import org.rogotulka.rozhkovaanna.server.api.request.RssRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApiClientImpl implements ApiClient {

    private Map<Class<? extends Request<?>>, RequestExecutor> mMap = new HashMap<>();

    public ApiClientImpl() {

        mMap.put(RssRequest.class, new RequestExecutor<RssRequest, List<News>>() {
            @Override
            public List<News> execute(RssRequest rssRequest) throws IOException {
                return getNewsList(rssRequest);
            }
        });
    }

    @SuppressWarnings({"SuspiciousMethodCalls", "unchecked"})
    @Override
    public <ResponseType> ResponseType execute(Request<ResponseType> request) throws IOException {
        if (!mMap.containsKey(request.getClass())) {
            throw new UnsupportedOperationException("Request is not supported or implemented: " + request.getClass().getCanonicalName());
        }
        return (ResponseType) mMap.get(request.getClass()).execute(request);
    }

    private List<News> getNewsList(RssRequest request) {
        List<News> result = new ArrayList<>();
        Network network = new Network();
        try {
            InputStream response = network.getInputStream(request.getSource().getSourceString());
            XMLParser parser = new XMLParser();
            result = parser.parseNews(request.getSource(), response);
        } catch (IOException e) {
            // TODO
        } finally {
            network.close();
        }

        return result;
    }
}
