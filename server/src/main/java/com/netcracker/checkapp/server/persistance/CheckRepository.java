package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.check.Check;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends MongoRepository<Check, String> {

    // method name should contain correct parameters names!!
    // see the bad example below
    //check findByCredentials(Integer fiscalDocumentNumber, Integer fiscalSign, Integer fiscalDriveNumber);

    // it may be more reliable to find check by custom method, using expanding interface CheckRepositoryCustom

    Check findById(String id);

    boolean existsByIdAndUsername(String id, String username);

    List<Check> findByUsername(String login);

    List<Check> findByUsernameAndShortPlaceCoordsNear(String username,Point coords, Distance radius);

}
