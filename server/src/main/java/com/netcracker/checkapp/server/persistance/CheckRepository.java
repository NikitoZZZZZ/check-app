package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.Check;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckRepository extends MongoRepository<Check, String> {

    Check findByCredentials(Integer fiscalDocumentNumber, Integer fiscalSign, Integer fiscalDriveNumber);

    boolean saveCheck();


}
