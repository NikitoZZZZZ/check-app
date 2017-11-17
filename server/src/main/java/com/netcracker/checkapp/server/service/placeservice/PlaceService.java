package com.netcracker.checkapp.server.service.placeservice;

import com.netcracker.checkapp.server.model.Place;
import com.sun.prism.image.Coords;

import java.util.List;

public interface PlaceService {
    public Place incrementPlaceRating(Place place);

    public Place addNewPlace(Place place);

    public List<Place> getNearPlaces(Coords coords, double radius);
}
