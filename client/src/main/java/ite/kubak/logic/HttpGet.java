package ite.kubak.logic;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpGet {
    HttpClient client;

    public HttpGet(){
        client = HttpClient.newHttpClient();
    }

    public HttpRequest create_request(String URL){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();
        return request;
    }

    public HttpResponse<String> get_response(HttpRequest request){
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (Exception e){return null;}
    }

    public HttpClient getClient() {
        return client;
    }
}
