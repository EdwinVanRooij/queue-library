package com.nonexistentcompany.lib.domain;

import java.util.List;

public class RichRoute {
    private String id;
    private String origin;
    private Double price;
    private int distance;
    private int vat;
    private List<RichRouteDetail> details;

    public RichRoute(String id, String origin, Double price, int distance, int vat, List<RichRouteDetail> details) {
        this.id = id;
        this.origin = origin;
        this.price = price;
        this.distance = distance;
        this.vat = vat;
        this.details = details;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public List<RichRouteDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RichRouteDetail> details) {
        this.details = details;
    }
}
