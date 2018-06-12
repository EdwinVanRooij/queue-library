package com.nonexistentcompany.lib.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForeignRoute {

    private String id;
    private int vehicleWeight; // must be filled in, even if it's randomly generated
    private List<List<EULocation>> trips;
    private String origin; // CountryCode that defines the country where the car is registered

    public ForeignRoute(String origin, List<List<EULocation>> trips, String id) {
        this.origin = origin;
        this.trips = trips;
        this.id = id;
        this.vehicleWeight = 1000 + new Random().nextInt(1000);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<List<EULocation>> getTrips() {
        return trips;
    }

    public void setTrips(List<List<EULocation>> trips) {
        this.trips = trips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ForeignRoute{" +
                "id='" + id + '\'' +
                ", vehicleWeight='" + vehicleWeight + '\'' +
                ", trips=" + trips +
                ", origin='" + origin + '\'' +
                '}';
    }
}
