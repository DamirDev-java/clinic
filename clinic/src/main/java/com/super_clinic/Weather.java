package com.super_clinic;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class Weather {

	private static final String API_KEY =  "56b30cb255.3443075"; // Ваш API ключ Gismeteo
    private static final String CITY_NAME = "москв"; // Название города для которого хотите получить информацию

    public void getWeather() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.gismeteo.net/v2/search/cities/?lang=en&query=" + CITY_NAME))
                .header("X-Gismeteo-Token", API_KEY)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
                System.out.println("Информация о городе " + CITY_NAME + ": " + response.body());
                // Здесь вы можете распарсить JSON-ответ и извлечь нужные данные о городе и его погоде
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
