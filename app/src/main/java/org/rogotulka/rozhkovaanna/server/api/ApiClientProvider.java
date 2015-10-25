package org.rogotulka.rozhkovaanna.server.api;

public class ApiClientProvider {

    public static ApiClient getApiClient() {
        return new ApiClientImpl();
    }
}
