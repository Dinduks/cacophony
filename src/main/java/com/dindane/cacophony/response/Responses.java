package com.dindane.cacophony.response;

import java.nio.file.Path;

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

    public static Response Ok(Path path) {
        return FileResponseBuilder(path, 200);
    }

    public static Response BadRequest(Path path) {
        return FileResponseBuilder(path, 400);
    }

    public static Response NotFound(Path path) {
        return FileResponseBuilder(path, 404);
    }

    private static Response FileResponseBuilder(Path path, int statusCode) {
        return new FileResponse(statusCode, path);
    }

    private static Response textResponseBuilder(String content, int statusCode) {
        return new TextResponse(statusCode, content);
    }
}
