package com.netcracker.checkapp.server.service.fdspservice;

import com.netcracker.checkapp.server.model.FDSP;
import com.netcracker.checkapp.server.persistance.FDSPRepository;

public class FDSPServiceImpl implements FDSPService {
    FDSPRepository fdspRepository;

    FDSPServiceImpl(FDSPRepository fdspRepository) {
        this.fdspRepository = fdspRepository;
    }

    @Override
    public FDSP addFDSP(FDSP fdsp) {
        if (findFDSP(fdsp.getFiscalDriveNumber()) == null) {
            return fdspRepository.save(fdsp);
        } else {
            return fdspRepository.findOne(fdsp.getId());
        }
    }

    @Override
    public FDSP findFDSP(String fiscalDriveNumber) {
        return fdspRepository.findByFiscalDriveNumber(fiscalDriveNumber);
    }
}
