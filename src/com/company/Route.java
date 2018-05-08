package com.company;

import java.util.List;

public class Route {
    private int distance;
    private int price;
    private int vat;
    private String license;
    private String country;
    private List<Rate> rates;

    public Route(int distance, int price, int vat, String license, String country, List<Rate> rates) {
        this.distance = distance;
        this.price = price;
        this.vat = vat;
        this.license = license;
        this.country = country;
        this.rates = rates;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
