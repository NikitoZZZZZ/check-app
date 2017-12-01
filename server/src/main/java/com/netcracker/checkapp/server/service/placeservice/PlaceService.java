package com.netcracker.checkapp.server.service.placeservice;


import com.netcracker.checkapp.server.model.place.Coords;
import com.netcracker.checkapp.server.model.place.Place;

import java.util.List;

public interface PlaceService {
    public Place incrementPlaceRating(Place place);

    List<Place> getNearPlaces(Coords coords, double radius);
    public Place addNewPlace(Place place);
}
