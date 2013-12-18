package com.dindane.cacophony.response;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class RedirectResponse extends Response {
    private String targetURL;

    public RedirectResponse(String targetURL) {
        this.targetURL = targetURL;
    }

    @Override
    public void send(HttpServerExchange exchange) {
        exchange.setResponseCode(302);
        exchange.getResponseHeaders().put(new HttpString("Location"), targetURL);
        exchange.endExchange();
    }
}
