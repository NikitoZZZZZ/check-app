package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.check.Check;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends MongoRepository<Check, String> {

    boolean existsByIdAndUsername(String id, String username);

    List<Check> findByUsername(String username);

}
