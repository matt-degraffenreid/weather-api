package org.cresta.weather.gateway;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocationGateway {
    @Value("${spring.google.apiKey}")
    private String googleApiKey;
    private GeoApiContext context;

    public LatLng getLocation(String location) throws Exception {
            this.context = new GeoApiContext.Builder().apiKey(this.googleApiKey).build();
            GeocodingApiRequest request = GeocodingApi.geocode(this.context, location);
            GeocodingResult[] result;

            try{
                result = request.await();
            }
            catch(Exception ex){
                throw new Exception("Something went wrong in the location gateway");
            }

            if(result.length == 0)
                throw new NotFoundException(String.format("The requested location (%s) cannot be found", location));
            return result[0].geometry.location;
    }
}
