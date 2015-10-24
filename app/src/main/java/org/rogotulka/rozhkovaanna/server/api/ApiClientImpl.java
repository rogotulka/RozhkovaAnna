package org.rogotulka.rozhkovaanna.server.api;

import org.rogotulka.rozhkovaanna.server.api.request.Request;

import java.io.IOException;

public class ApiClientImpl implements ApiClient {

    @Override
    public <ResponseType> ResponseType execute(Request<ResponseType> request) throws IOException, InterruptedException {
        return null;
    }
}
