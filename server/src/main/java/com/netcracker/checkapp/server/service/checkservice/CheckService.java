package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.service.HttpService;

public interface CheckService extends HttpService {
    public Check getCheck(String fiscalDocumentNumber, String fiscalDriveNumber, String fiscalSign);
}