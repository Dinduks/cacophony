package com.dindane.cacophony;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Route {
    private String url;
    private Method method;
    private Map<String, String> headers = new HashMap<>();

    public Route(String url, Method method, Map<String, String> headers) {
        this.url = (url.charAt(0) != '/') ? '/' + url : url;
        this.method = method;
        this.headers.putAll(headers);
        this.headers.put("X-PoweredBy", "Cacophony");
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }
}
