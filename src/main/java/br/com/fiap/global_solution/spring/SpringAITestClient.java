package br.com.fiap.global_solution.spring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpringAITestClient {

    public static void main(String[] args) {
        String endpointUrl = "http://localhost:8080/springai/generate";
        String userPrompt = "Java é a melhor linguagem de programação? Responda apenas sim ou não.";

        try {
            URL url = new URL(endpointUrl + "?message=" + java.net.URLEncoder.encode(userPrompt, "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;

            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            conn.disconnect();
            System.out.println("Response from SpringAIChatController: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
