package com.nonexistentcompany.domain;


import java.util.ArrayList;
import java.util.List;

public class Route {

    private String originCountry; // defines the country where the car is registered
    private String drivenInCountry; // defines the country in which the car has driven
    private List<EULocation> locationList;
    private String licensePlate;

    public Route(String originCountry, String drivenInCountry, String licensePlate) {
        this.originCountry = originCountry;
        this.drivenInCountry = drivenInCountry;
        this.locationList = new ArrayList<>();
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDrivenInCountry() {
        return drivenInCountry;
    }

    public void setDrivenInCountry(String drivenInCountry) {
        this.drivenInCountry = drivenInCountry;
    }

    public void addLocation(EULocation location) {
        this.locationList.add(location);
    }

    public List<EULocation> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<EULocation> locationList) {
        this.locationList = locationList;
    }

}
