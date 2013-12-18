package com.dindane.cacophony.response;

import io.undertow.server.HttpServerExchange;

abstract public class Response {
    protected int statusCode;

    abstract public void send(HttpServerExchange exchange);

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
