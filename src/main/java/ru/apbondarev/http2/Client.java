package ru.apbondarev.http2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.net.http.HttpClient.Version.HTTP_2;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HTTP_2) // just to show off; HTTP/2 is the default
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://localhost:8443/hello?name=World"))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
