package com.dindane.cacophony.response;

import java.nio.ByteBuffer;

public class Response {
    private ByteBuffer content;
    private String contentType;
    private int statusCode;

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public ByteBuffer getContent() {
        return content;
    }

    public void setContent(ByteBuffer content) {
        this.content = content;
    }

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
