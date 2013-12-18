package com.dindane.cacophony.response;

import java.nio.ByteBuffer;

public class Responses {
    public static Response Ok(String content) {
        return responseBuilder(content, 200);
    }

    public static Response BadRequest(String content) {
        return responseBuilder(content, 400);
    }

    public static Response NotFound(String content) {
        return responseBuilder(content, 404);
    }

    private static Response responseBuilder(String content, int statusCode) {
        Response response = new Response(statusCode);
        response.setContent(ByteBuffer.wrap(content.getBytes()));
        return response;
    }
}
