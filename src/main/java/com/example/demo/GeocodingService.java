package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class GeocodingService {


    public GeoCoordinates geocode(String country) {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + country;

        RestTemplate restTemplate = new RestTemplate();
        Object[] response = restTemplate.getForObject(url, Object[].class);

        if (response != null && response.length > 0) {
            Map<String, Object> result = (Map<String, Object>) response[0];
            double lat = Double.parseDouble(result.get("lat").toString());
            double lng = Double.parseDouble(result.get("lon").toString());
            return new GeoCoordinates(lat, lng);
        }
        return null;

    }



}
