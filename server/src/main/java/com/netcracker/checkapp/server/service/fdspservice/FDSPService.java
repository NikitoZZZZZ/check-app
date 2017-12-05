package com.netcracker.checkapp.server.service.fdspservice;

import com.netcracker.checkapp.server.model.FDSP;

public interface FDSPService {
    FDSP addFDSP(FDSP fdsp);

    FDSP findFDSP(String fiscalDriveNumber);
}
