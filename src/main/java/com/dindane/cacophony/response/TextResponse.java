package com.dindane.cacophony.response;

import io.undertow.server.HttpServerExchange;

import java.nio.ByteBuffer;

public class TextResponse extends Response {
    private ByteBuffer content;

    public TextResponse(int statusCode, String content) {
        this.content = ByteBuffer.wrap(content.getBytes());
        setStatusCode(statusCode);
    }

    public void send(HttpServerExchange exchange) {
        exchange.getResponseSender().send(content);
    }
}
