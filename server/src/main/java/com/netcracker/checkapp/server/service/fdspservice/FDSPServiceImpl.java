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
        if (!fdspRepository.existsByFiscalDriveNumber(fdsp.getFiscalDriveNumber())) {
            return fdspRepository.save(fdsp);
        } else {
            return fdspRepository.findOne(fdsp.getId());
        }
    }
}
