package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.FDSP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FDSPRepository extends MongoRepository<FDSP,String> {

}
