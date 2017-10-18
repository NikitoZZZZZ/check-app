package com.netcracker.checkapp.server.model;

public class Item {
    private Integer price;
    private Integer ndsSum;
    private Integer quantity;
    private String name;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNdsSum() {
        return ndsSum;
    }

    public void setNdsSum(Integer ndsSum) {
        this.ndsSum = ndsSum;
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