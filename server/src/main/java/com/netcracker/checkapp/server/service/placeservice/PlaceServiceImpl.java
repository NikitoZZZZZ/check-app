package com.netcracker.checkapp.server.service.placeservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.model.place.Coords;
import com.netcracker.checkapp.server.model.place.Place;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import com.netcracker.checkapp.server.persistance.PlaceRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    PlaceRepository placeRepository;
    CheckRepository checkRepository;

    PlaceServiceImpl(PlaceRepository placeRepository,CheckRepository checkRepository) {
        this.placeRepository = placeRepository;
        this.checkRepository = checkRepository;
    }

    @Override
    public Place incrementPlaceRating(Place place) {
        if (placeRepository.updatePlace(place.getId()) != 0)
            return placeRepository.findOne(place.getId());
        else
            return null;
    }

    @Override
    public Place addNewPlace(Place place) {
        Place localPlace = placeRepository.findOne(place.getId());
        if (localPlace != null)
            localPlace = incrementPlaceRating(place);
        else
            localPlace = placeRepository.save(place);
        return localPlace;
    }

    @Override
    public List<Check> getNearPlacesAndChecks(String longitude, String latitude, String radius) {
        Distance distance = new Distance(Double.parseDouble(radius), Metrics.KILOMETERS);
        Point coords = new Point(Double.parseDouble(longitude),Double.parseDouble(latitude));
        return checkRepository.findByShortPlaceCoordsNear(coords, distance);
    }
}
