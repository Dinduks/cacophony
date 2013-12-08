package com.dindane.cacophony;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.Headers;

import java.util.HashMap;

import static io.undertow.Handlers.path;

public class App {
    private HashMap<Route, Action> routes = new HashMap<>();
    private String hostname;
    private int port;

    public App(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void get(String url, Action action) {
        Route route = new Route(url, Method.GET);
        routes.put(route, action);
    }

    public void run() {
        PathHandler path = path();
        routes.entrySet().stream().forEach(entrySet -> {
            path.addPath(entrySet.getKey().getRoute(), exchange -> {
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send(entrySet.getValue().accept());
            });
        });

        Undertow server = Undertow.builder()
                .addListener(port, hostname)
                .setHandler(path)
                .build();
        server.start();
    }
}
