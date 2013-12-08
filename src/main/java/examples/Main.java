package examples;

import com.dindane.cacophony.App;

public class Main {
    public static void main(String[] args) {
        new App("localhost", 8080) {{
            get("/hello", (params) -> {
                return "Hello world!";
            });

            get("/test-params", (params) -> {
                return params.toString();
            });
        }}.run();
    }
}
