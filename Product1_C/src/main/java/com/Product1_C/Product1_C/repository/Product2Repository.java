package com.Product1_C.Product1_C.repository;


import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Repository
public class Product2Repository {

    public Boolean existProduct(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/products?sku=" + sku))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        var code = response.statusCode();
        return code == 200;
    }
}
