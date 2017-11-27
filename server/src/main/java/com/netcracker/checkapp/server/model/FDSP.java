package com.netcracker.checkapp.server.model;

import com.netcracker.checkapp.server.model.place.ShortPlace;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Fiscal Drive Number and Short Place document class
 */
@Document(collection = "fdsp")
public class FDSP {

    private String fiscalDriveNumber;
    private ShortPlace shortPlace;

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public ShortPlace getShortPlace() {
        return shortPlace;
    }

    public void setShortPlace(ShortPlace shortPlace) {
        this.shortPlace = shortPlace;
    }
}
