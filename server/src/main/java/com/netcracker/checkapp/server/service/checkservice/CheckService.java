package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.check.Check;
import com.netcracker.checkapp.server.service.HttpService;

public interface CheckService extends HttpService {
    public Check getCheck(String fiscalDriveNumber, String fiscalDocumentNumber, String fiscalSign);
}