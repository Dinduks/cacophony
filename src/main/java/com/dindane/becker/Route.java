package com.dindane.becker;

public class Route {
    private String route;
    private Method method;

    public Route(String route, Method method) {
        if (route.charAt(0) != '/') {
            this.route = "/" + route;
        } else {
            this.route = route;
        }

        this.method = method;
    }

    public String getRoute() {
        return route;
    }
}
