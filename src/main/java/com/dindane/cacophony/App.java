package com.dindane.cacophony;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.HttpString;

import java.util.HashMap;
import java.util.Map;

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
        get(url, new HashMap<>(), action);
    }

    public void get(String url, Map<String, String> headers, Action action) {
        routes.put(new Route(url, Method.GET, headers), action);
    }

    public void run() {
        PathHandler path = path();
        routes.entrySet().stream().forEach(entrySet -> {
            path.addPath(entrySet.getKey().getUrl(), exchange -> {
                entrySet.getKey().getHeaders().entrySet().stream().forEach((e) -> {
                    exchange.getResponseHeaders().put(new HttpString(e.getKey()),
                            e.getValue());
                });

                exchange.getResponseSender().send(
                        entrySet.getValue().accept(exchange.getQueryParameters()));
            });
        });

        Undertow server = Undertow.builder()
                .addListener(port, hostname)
                .setHandler(path)
                .build();
        server.start();
    }
}
