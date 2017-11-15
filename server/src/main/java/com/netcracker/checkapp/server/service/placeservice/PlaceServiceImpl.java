package com.netcracker.checkapp.server.service.placeservice;

import com.netcracker.checkapp.server.model.Place;
import com.netcracker.checkapp.server.persistance.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {
    PlaceRepository placeRepository;

    PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place incrementPlaceRating(Place place) {
        return placeRepository.findCustomOneAndUpdate(place.getId());
    }
}
