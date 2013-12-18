package com.dindane.cacophony;

import com.dindane.cacophony.response.Response;
import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.FileTemplateLoader;
import de.neuland.jade4j.template.JadeTemplate;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.HttpString;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    private HashMap<Route, Action> routes = new HashMap<>();
    private String hostname;
    private int port;
    private JadeConfiguration jadeConfig;

    public App(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.jadeConfig = new JadeConfiguration();
    }

    public void setTemplatesDir(String path) {
        String templatesDir = (path.charAt(path.length()-1) != '/') ? path + '/' : path;
        jadeConfig.setTemplateLoader(new FileTemplateLoader(templatesDir, "UTF-8"));
    }

    public void get(String url, Action action) {
        get(url, new HashMap<>(), action);
    }

    public void get(String url, Map<String, String> headers, Action action) {
        routes.put(new Route(url, Method.GET, headers), action);
    }

    public String render(String templateName,
                         HashMap<String, Object> parameters) {
        try {
            JadeTemplate template = jadeConfig.getTemplate(templateName);
            return Jade4J.render(template, parameters);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void run() {
        PathHandler path = new PathHandler();

        routes.forEach((route, action) -> {
            path.addExactPath(route.getUrl(), exchange -> {
                route.getHeaders().forEach((k, v) -> {
                    exchange.getResponseHeaders().put(new HttpString(k), v);
                });

                Response response = action.accept(exchange.getQueryParameters());

                exchange.setResponseCode(response.getStatusCode());
                exchange.getResponseSender().send(response.getContent());
            });
        });

        Undertow server = Undertow.builder()
                .addListener(port, hostname)
                .setHandler(path)
                .build();
        server.start();
    }
}
