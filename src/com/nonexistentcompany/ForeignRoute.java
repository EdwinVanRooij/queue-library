package com.nonexistentcompany;

import java.util.List;

public class ForeignRoute {
    private List<Location> locationList;
    private String countryCode;

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

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}
