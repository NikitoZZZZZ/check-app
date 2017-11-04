package com.netcracker.checkapp.server.service.checkservice;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.service.Service;

public interface CheckService extends Service {
    public Check getCheck(String fiscalDocumentNumber, String fiscalDriveNumber, String fiscalSign);
}