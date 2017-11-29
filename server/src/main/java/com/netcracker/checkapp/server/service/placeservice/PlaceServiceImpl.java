package com.netcracker.checkapp.server.service.placeservice;


import com.netcracker.checkapp.server.model.place.Place;
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

}
