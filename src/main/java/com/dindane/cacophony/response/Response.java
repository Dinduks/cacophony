package com.dindane.cacophony.response;

import io.undertow.server.HttpServerExchange;

abstract public class Response {
    protected String contentType;
    protected int statusCode;

    abstract public void send(HttpServerExchange exchange);

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
