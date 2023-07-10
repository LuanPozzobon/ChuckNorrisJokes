package com.luan.chucknorrisj;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

/**
 *
 * @author luanp
 */
public class ClientHttp {
    private String getBody(String url){
        try{
            URI address = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            
            return response.body();
        }
        catch(IOException | InterruptedException e){
            throw new ClientHttpException("Error querying the URL: " + e);
        }
    }
    
    public String getJoke(String url){
        JsonObject jsonObject = JsonParser.parseString(getBody(url)).getAsJsonObject();
        return jsonObject.get("value").getAsString();
    }
    
    public List<String> getCategories(String url){
        String body = getBody(url);
        Gson gson = new Gson();
        
        return gson.fromJson(body, List.class);
    }
}
