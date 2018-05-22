package com.nonexistentcompany.domain;

import java.util.List;

public class RichRoute {

    private String originCountry; // defines the country where the car is registered
    private String drivenInCountry; // defines the country in which the car has driven

    private int distance;
    private int price;
    private int vat;
    private String license;
    private List<Rate> rates;

    public RichRoute(String originCountry, String drivenInCountry, int distance, int price, int vat, String license, String country, List<Rate> rates) {
        this.originCountry = originCountry;
        this.drivenInCountry = drivenInCountry;
        this.distance = distance;
        this.price = price;
        this.vat = vat;
        this.license = license;
        this.rates = rates;
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

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
