package com.netcracker.checkapp.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class NalogRuCheck {
    private static final String DEFAULT_NDS18 = "0";
    private static final String DEFAULT_NDS10 = "0";

    private String fiscalDocumentNumber;
    private String fiscalDriveNumber;
    private String fiscalSign;
    @JsonProperty(required = false)
    private String nds10;
    @JsonProperty(required = false)
    private String nds18;
    private String totalSum;
    private String dateTime;
    private List<Item> items;

    public NalogRuCheck() {
        this.nds18 = DEFAULT_NDS18;
        this.nds10 = DEFAULT_NDS10;
    }

    public String getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(String fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public String getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public String getNds10() {
        return nds10;
    }

    public void setNds10(String nds10) {
        this.nds10 = nds10;
    }

    public String getNds18() { return nds18; }

    public void setNds18(String nds18) {
        this.nds18 = nds18;
    }

    public String getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(String totalSum) {
        this.totalSum = totalSum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Check{fiscalDocumentNumber=" + fiscalDocumentNumber +
                ", fiscalDriveNumber=" + fiscalDriveNumber +
                ", fiscalSign=" + fiscalSign +
                ", nds10=" + nds10 +
                ", nds18=" + nds18 +
                ", totalSum=" + totalSum +
                ", dateTime=" + dateTime +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NalogRuCheck)) return false;

        NalogRuCheck check = (NalogRuCheck) o;

        if (!fiscalDocumentNumber.equals(check.fiscalDocumentNumber)) return false;
        if (!fiscalDriveNumber.equals(check.fiscalDriveNumber)) return false;
        if (!fiscalSign.equals(check.fiscalSign)) return false;
        if (!nds10.equals(check.nds10)) return false;
        if (!nds18.equals(check.nds18)) return false;
        if (!totalSum.equals(check.totalSum)) return false;
        if (!dateTime.equals(check.dateTime)) return false;
        return items.equals(check.items);
    }

    @Override
    public int hashCode() {
        int result = fiscalDocumentNumber.hashCode();
        result = 31 * result + fiscalDriveNumber.hashCode();
        result = 31 * result + fiscalSign.hashCode();
        result = 31 * result + nds10.hashCode();
        result = 31 * result + nds18.hashCode();
        result = 31 * result + totalSum.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + items.hashCode();
        return result;
    }

}

