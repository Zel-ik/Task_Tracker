package manager;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVClient {
    private String url;
    private String token = "";
    private HttpClient client = HttpClient.newHttpClient();

    public KVClient(String url) {
        this.url = url;
    }

    private HttpRequest.Builder buildRequestFor(String path) {
        HttpRequest.Builder request = HttpRequest.newBuilder().uri(URI.create(url + path));
        return request;
    }

    public void register() {
        HttpRequest request = buildRequestFor("/register").GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            token = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, String json) {
        HttpRequest request = buildRequestFor("/save/" + key + "?API_TOKEN" + token).
                POST(HttpRequest.BodyPublishers.ofString(json)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String load(String key) {
        HttpRequest request = buildRequestFor("/load/" + key + "?API_TOKEN" + token).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "{}";
    }
}
