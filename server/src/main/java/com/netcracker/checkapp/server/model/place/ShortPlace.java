package com.netcracker.checkapp.server.model.place;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class ShortPlace {

    private String id;
    private String name;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE) private Coords coords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortPlace)) return false;

        ShortPlace that = (ShortPlace) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

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

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
