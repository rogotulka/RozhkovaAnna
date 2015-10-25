package org.rogotulka.rozhkovaanna.logic;

import org.rogotulka.rozhkovaanna.server.api.ApiClientProvider;

public class LogicProvider {

    public static Logic getLogic() {
        return new LogicImpl(ApiClientProvider.getApiClient());
    }
}
