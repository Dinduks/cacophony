package com.dindane.cacophony.response;

import com.dindane.cacophony.Util;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class FileResponse extends Response {
    private FileChannel content;
    private String contentType;

    public FileResponse(int statusCode, Path path) {
        setStatusCode(statusCode);
        try {
            this.content = FileChannel.open(path);
            this.contentType = Util.tryToDetectMimeType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(HttpServerExchange exchange) {
        if (content == null) {
            exchange.setResponseCode(500);
            exchange.endExchange();
            return;
        }

        HttpString headerName = new HttpString("Content-Type");
        if (!exchange.getResponseHeaders().contains(headerName) &&
                contentType != null) {
            exchange.getResponseHeaders().put(headerName,
                    contentType);
        }
        exchange.getResponseSender()
                .transferFrom(content, new FileResponseIoCallback());
    }

    class FileResponseIoCallback implements IoCallback {
        @Override
        public void onComplete(HttpServerExchange exchange, Sender sender) {
            exchange.endExchange();
            closeFileChannel();
        }

        @Override
        public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {
            exchange.endExchange();
            closeFileChannel();
        }

        private void closeFileChannel() {
            try {
                content.close();
            } catch (IOException e) {
                System.err.println("Could not close the file channel.");
            }
        }
    }
}
