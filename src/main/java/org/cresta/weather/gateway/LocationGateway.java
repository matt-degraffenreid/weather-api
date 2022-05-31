package org.cresta.weather.gateway;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
@Component
public class LocationGateway {
    @Value("${spring.google.apiKey}")
    private String googleApiKey;
    private GeoApiContext context;

    public LatLng getLocation(String location) throws IOException, InterruptedException, ApiException {
        this.context = new GeoApiContext.Builder().apiKey(this.googleApiKey).build();
        GeocodingApiRequest request = GeocodingApi.geocode(this.context, location);
        GeocodingResult[] result = request.await();
        if(result.length == 0)
            return null;
        return result[0].geometry.location;
    }
}
