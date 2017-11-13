package com.netcracker.checkapp.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private BigDecimal price;
    @JsonProperty(required = false)
    private BigDecimal ndsSum;
    @JsonProperty(required = false)
    private BigDecimal nds10;
    @JsonProperty(required = false)
    private BigDecimal nds18;
    private Integer quantity;
    private String name;

    public Item() {
        this.ndsSum = new BigDecimal(0);
        this.nds10 = new BigDecimal(0);
        this.nds18 = new BigDecimal(0);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNdsSum() {
        return ndsSum;
    }

    public void setNdsSum(BigDecimal ndsSum) {
        this.ndsSum = ndsSum;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", ndsSum=" + ndsSum +
                ", nds10=" + nds10 +
                ", nds18=" + nds18 +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + ndsSum.hashCode();
        result = 31 * result + nds10.hashCode();
        result = 31 * result + nds18.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!price.equals(item.price)) return false;
        if (!ndsSum.equals(item.ndsSum)) return false;
        if (!nds10.equals(item.nds10)) return false;
        if (!nds18.equals(item.nds18)) return false;
        if (!quantity.equals(item.quantity)) return false;
        return name.equals(item.name);
    }
}