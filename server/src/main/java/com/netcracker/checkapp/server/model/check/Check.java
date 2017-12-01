package com.netcracker.checkapp.server.model.check;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.netcracker.checkapp.server.model.place.ShortPlace;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "checks")
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Check implements Serializable {

    @Id
    private String id;
    private String fiscalDocumentNumber;
    private String fiscalDriveNumber;
    private String fiscalSign;
    private BigDecimal nds10;
    private BigDecimal nds18;
    private BigDecimal totalSum;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateTime;
    private List<Item> items;
    private String username;
    private ShortPlace shortPlace;

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getNds10() {
        return nds10;
    }

    public void setNds10(BigDecimal nds10) {
        this.nds10 = nds10;
    }

    public BigDecimal getNds18() {
        return nds18;
    }

    public void setNds18(BigDecimal nds18) {
        this.nds18 = nds18;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ShortPlace getShortPlace() {
        return shortPlace;
    }

    public void setShortPlace(ShortPlace shortPlace) {
        this.shortPlace = shortPlace;
    }

    @Override
    public String toString() {
        return "check{" +
                "id='" + id + '\'' +
                ", fiscalDocumentNumber='" + fiscalDocumentNumber + '\'' +
                ", fiscalDriveNumber='" + fiscalDriveNumber + '\'' +
                ", fiscalSign='" + fiscalSign + '\'' +
                ", nds10=" + nds10 +
                ", nds18=" + nds18 +
                ", totalSum=" + totalSum +
                ", dateTime=" + dateTime +
                ", items=" + items +
                ", username='" + username + '\'' +
                ", shortPlace=" + shortPlace +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Check)) return false;

        Check check = (Check) o;

        if (!id.equals(check.id)) return false;
        if (!fiscalDocumentNumber.equals(check.fiscalDocumentNumber)) return false;
        if (!fiscalDriveNumber.equals(check.fiscalDriveNumber)) return false;
        if (!fiscalSign.equals(check.fiscalSign)) return false;
        if (!nds10.equals(check.nds10)) return false;
        if (!nds18.equals(check.nds18)) return false;
        if (!totalSum.equals(check.totalSum)) return false;
        if (!dateTime.equals(check.dateTime)) return false;
        if (!items.equals(check.items)) return false;
        if (!username.equals(check.username)) return false;
        return shortPlace.equals(check.shortPlace);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fiscalDocumentNumber.hashCode();
        result = 31 * result + fiscalDriveNumber.hashCode();
        result = 31 * result + fiscalSign.hashCode();
        result = 31 * result + nds10.hashCode();
        result = 31 * result + nds18.hashCode();
        result = 31 * result + totalSum.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + items.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + shortPlace.hashCode();
        return result;
    }
}