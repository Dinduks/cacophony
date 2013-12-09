package examples;

import com.dindane.cacophony.App;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        new App("localhost", 8080) {{
            setTemplatesDir("src/main/java/examples/templates");

            get("/hello", (params) -> "Hello world!");

            get("/params-example", (params) -> {
                return params.toString();
            });

            HashMap<String, String> headers = new HashMap<>();
            headers.put("Hey!", "It works!");
            get("/headers-example", headers, (params) -> {
                return "Check the response headers!";
            });

            get("/template-example", (params) -> { // Visit /test-template?name=Samy
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("pageTitle", "Template example");
                parameters.put("name", params.get("name").getFirst());
                return render("index", parameters);
            });
        }}.run();
    }
}
