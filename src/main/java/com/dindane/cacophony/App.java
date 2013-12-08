package com.dindane.cacophony;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.HttpString;

import java.util.HashMap;
import java.util.Map;

public class App {
    private HashMap<Route, Action> routes = new HashMap<>();
    private String hostname;
    private int port;

    public App(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void get(String url, Action action) {
        get(url, new HashMap<>(), action);
    }

    public void get(String url, Map<String, String> headers, Action action) {
        routes.put(new Route(url, Method.GET, headers), action);
    }

    public void run() {
        PathHandler path = new PathHandler();

        routes.forEach((route, action) -> {
            path.addPath(route.getUrl(), exchange -> {
                route.getHeaders().forEach((k, v) -> {
                    exchange.getResponseHeaders().put(new HttpString(k), v);
                });

                exchange.getResponseSender().send(
                        action.accept(exchange.getQueryParameters()));
            });
        });

        Undertow server = Undertow.builder()
                .addListener(port, hostname)
                .setHandler(path)
                .build();
        server.start();
    }
}
