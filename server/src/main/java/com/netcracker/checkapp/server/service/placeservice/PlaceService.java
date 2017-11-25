package com.netcracker.checkapp.server.service.placeservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.model.place.Coords;
import com.netcracker.checkapp.server.model.place.Place;
import org.springframework.data.geo.Distance;

import java.util.List;

public interface PlaceService {
    public Place incrementPlaceRating(Place place);

    public Place addNewPlace(Place place);

    public List<Check> getNearPlacesAndChecks(String  longitude, String latitude, String radius);
}
