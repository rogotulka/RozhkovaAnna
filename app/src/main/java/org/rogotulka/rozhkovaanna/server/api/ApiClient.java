package org.rogotulka.rozhkovaanna.server.api;

import org.rogotulka.rozhkovaanna.server.api.request.Request;

import java.io.IOException;

public interface ApiClient {

    <ResponseType> ResponseType execute(Request<ResponseType> request)
            throws IOException, InterruptedException;
}
