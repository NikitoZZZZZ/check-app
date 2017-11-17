package com.netcracker.checkapp.server.persistance.customs;

import com.netcracker.checkapp.server.model.Place;

public interface PlaceRepositoryCustom {

    /*
        this method increments the field 'numOfChecks' in Place object
        and it should be implemented customly
     */
    int updatePlace(String id);
}
