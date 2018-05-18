package com.nonexistentcompany;

import java.util.ArrayList;
import java.util.List;

public class ForeignRoute {
    private List<Location> locationList;
    private String countryCode;

    public ForeignRoute(String countryCode) {
        this.countryCode = countryCode;
        this.locationList = new ArrayList<>();
    }

    public ForeignRoute(List<Location> locationList, String countryCode) {
        this.locationList = locationList;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void addLocation(Location location) {
        this.locationList.add(location);
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}
