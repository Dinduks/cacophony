package com.dindane.cacophony.response;

import io.undertow.io.Sender;

import java.nio.ByteBuffer;

public class TextResponse extends Response {
    private ByteBuffer content;

    public TextResponse(int statusCode, String content) {
        this.content = ByteBuffer.wrap(content.getBytes());
        setStatusCode(statusCode);
    }

    public void send(Sender sender) {
        sender.send(content);
    }
}
