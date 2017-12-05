package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.service.security.HttpService;

import java.util.List;

public interface CheckService extends HttpService {
    Check getCheck(String fiscalDriveNumber, String fiscalDocumentNumber, String fiscalSign);

    List<Check> getNearPlacesAndChecks(String  longitude, String latitude, String radius);

    Check save(Check check);

    boolean existsByIdAndUsername(String id, String username);

    Check findById(String id);

    List<Check> findByUsername(String username);
}