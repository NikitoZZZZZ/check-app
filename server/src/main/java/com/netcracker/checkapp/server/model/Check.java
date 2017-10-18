package com.netcracker.checkapp.server.model;

import java.time.LocalDateTime;
import java.util.List;

public class Check {
    private Integer fiscalDocumentNumber;
    private Integer fiscalDriveNumber;
    private Integer fiscalSign;
    private Integer nds10;
    private Integer nds18;
    private Integer totalSum;
    private LocalDateTime dateTime;
    private List<Item> item;

    public Integer getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(Integer fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public Integer getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(Integer fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public Integer getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(Integer fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public Integer getNds10() {
        return nds10;
    }

    public void setNds10(Integer nds10) {
        this.nds10 = nds10;
    }

    public Integer getNds18() {
        return nds18;
    }

    public void setNds18(Integer nds18) {
        this.nds18 = nds18;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Check{" +
                "fiscalDocumentNumber=" + fiscalDocumentNumber +
                ", fiscalDriveNumber=" + fiscalDriveNumber +
                ", fiscalSign=" + fiscalSign +
                ", nds10=" + nds10 +
                ", nds18=" + nds18 +
                ", totalSum=" + totalSum +
                ", dateTime=" + dateTime +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Check)) return false;

        Check check = (Check) o;

        if (!fiscalDocumentNumber.equals(check.fiscalDocumentNumber)) return false;
        if (!fiscalDriveNumber.equals(check.fiscalDriveNumber)) return false;
        if (!fiscalSign.equals(check.fiscalSign)) return false;
        if (!nds10.equals(check.nds10)) return false;
        if (!nds18.equals(check.nds18)) return false;
        if (!totalSum.equals(check.totalSum)) return false;
        if (!dateTime.equals(check.dateTime)) return false;
        return item.equals(check.item);
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
        result = 31 * result + item.hashCode();
        return result;
    }
}