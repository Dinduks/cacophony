package examples;

import com.dindane.cacophony.App;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        new App("localhost", 8080) {{
            get("/hello", (params) -> {
                return "Hello world!";
            });

            get("/test-params", (params) -> {
                return params.toString();
            });

            HashMap<String, String> headers = new HashMap<>();
            headers.put("Hey!", "It works!");
            get("/test-headers", headers, (params) -> {
                return "Check the response headers!";
            });
        }}.run();
    }
}
