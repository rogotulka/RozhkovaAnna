package org.rogotulka.rozhkovaanna.server.api;

import java.io.IOException;

interface RequestExecutor<RequestType, ResponseType> {

    ResponseType execute(RequestType request) throws IOException;
}