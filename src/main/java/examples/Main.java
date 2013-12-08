package examples;

import com.dindane.becker.App;

public class Main {
    public static void main(String[] args) {
        new App("localhost", 8080) {{
            get("/hello", () -> {
                return "Hello world!";
            });

            get("/bye", () -> {
                return "Good bye!";
            });
        }}.run();
    }
}
