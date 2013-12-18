package com.dindane.cacophony.response;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class Responses {
    public static Response Ok(String content) {
        return textResponseBuilder(content, 200);
    }

    public static Response BadRequest(String content) {
        return textResponseBuilder(content, 400);
    }

    public static Response NotFound(String content) {
        return textResponseBuilder(content, 404);
    }

    private static Response textResponseBuilder(String content, int statusCode) {
        return new TextResponse(statusCode, content);
    }

}
