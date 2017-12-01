package com.netcracker.checkapp.server.model;

import com.netcracker.checkapp.server.model.place.ShortPlace;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Fiscal Drive Number and Short Place document class
 */
@Document(collection = "fdsp")
public class FDSP {

    @Id
    private String id;
    private String fiscalDriveNumber;
    private ShortPlace shortPlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


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
