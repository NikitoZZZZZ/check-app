package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.Place;
import com.sun.prism.image.Coords;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

    Place findById(String id);

    Place findByCoords(Coords coords);
}
