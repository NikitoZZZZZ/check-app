package com.netcracker.checkapp.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String price;
    private String ndsSum;
    private String quantity;
    private String name;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNdsSum() {
        return ndsSum;
    }

    public void setNdsSum(String ndsSum) {
        this.ndsSum = ndsSum;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + ndsSum.hashCode();
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
        if (!quantity.equals(item.quantity)) return false;
        return name.equals(item.name);
    }
}