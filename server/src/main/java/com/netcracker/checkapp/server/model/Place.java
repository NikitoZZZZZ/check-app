package com.netcracker.checkapp.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

class Coords {

    private double longitude;
    private double latitude;
}

class ShortPlace {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private String name;
    private String address;
    private Coords coords;
    private Double rating;
    private Integer numOfChecks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNumOfChecks() {
        return numOfChecks;
    }

    public void setNumOfChecks(Integer numOfChecks) {
        this.numOfChecks = numOfChecks;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coords=" + coords +
                ", rating=" + rating +
                ", numOfChecks=" + numOfChecks +
                ", address='" + address + '\'' +
                '}';
    }
}
