package com.flight.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class FlightTrackingService {
    private final WebClient webClient;

    @Value("${flight.tracking.api.url}") // Configure the API URL in application.properties
    private String apiUrl;

    @Value("${flight.tracking.api.key}") // Configure the API key in application.properties
    private String apiKey;

    public FlightTrackingService() {
        this.webClient = WebClient.builder().baseUrl(apiUrl).defaultHeader(HttpHeaders.AUTHORIZATION, apiKey).build();
    }

    public boolean hasFlightDeparted(String flightNumber) {
        String requestUrl = "/api/flights/" + flightNumber + "/status";

        // Send API request to get flight status information
        FlightStatusResponse response = webClient
                .method(HttpMethod.GET)
                .uri(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightStatusResponse.class)
                .block();

        // Check if the response indicates that the flight has departed
        if (response != null && response.getStatus().equals("DEPARTED")) {
            return true;
        }
        return false;
    }
}

