package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CheckRepository extends MongoRepository<Check, String> {

    // method name should contain correct parameters names!!
    // see the bad example below
    //Check findByCredentials(Integer fiscalDocumentNumber, Integer fiscalSign, Integer fiscalDriveNumber);

    // it may be more reliable to find check by custom method, using expanding interface CheckRepositoryCustom

    Check save(Check check);

    Check findById(String id);

    Check findByUser(User user);

}
