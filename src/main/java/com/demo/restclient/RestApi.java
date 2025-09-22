package com.demo.restclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestApi {

	public static void main(String[] args) {
		
        // Public API - No API Key needed
        String apiUrl = "https://official-joke-api.appspot.com/random_joke";

        // Create HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Create GET request
            HttpGet request = new HttpGet(URI.create(apiUrl));

            // Execute request
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    // Parse JSON response
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(response.getEntity().getContent());

                    String setup = jsonNode.get("setup").asText();
                    String punchline = jsonNode.get("punchline").asText();

                    // Display structured output
                    System.out.println("Here’s a Random Joke for you 😄");
                    System.out.println("--------------------------------");
                    System.out.println("Q: " + setup);
                    System.out.println("A: " + punchline);
                } else {
                    System.out.println("Failed! HTTP Error Code: " + response.getStatusLine().getStatusCode());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
