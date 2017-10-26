package com.netcracker.checkapp.server.persistance;

import com.netcracker.checkapp.server.model.Check;
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

    @Query("{user : {login : '?0', pwd : '?1'} }")
    List<Check> findByLoginAndPwd(String login, String pwd);

    // that's an example how to use @Query JSON request to find by one of fields
    @Query("{fiscalSign: '?0'}")
    Check findCustomByFiscalSign(String fiscalSign);

}
