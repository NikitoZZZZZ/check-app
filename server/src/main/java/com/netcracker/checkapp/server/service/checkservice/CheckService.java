package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.service.security.HttpService;

import java.util.List;

public interface CheckService extends HttpService {
    public Check getCheck(String fiscalDriveNumber, String fiscalDocumentNumber, String fiscalSign);

    public List<Check> getNearPlacesAndChecks(String  longitude, String latitude, String radius);
}