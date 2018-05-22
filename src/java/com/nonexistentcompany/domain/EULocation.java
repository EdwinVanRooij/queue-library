package com.nonexistentcompany.domain;

public class EULocation implements Comparable<EULocation> {
    private double lat;
    private double lon;
    private long unixTimestamp;
    private String license;
//
//    public EULocation(double lat, double lon) {
//        this.lat = lat;
//        this.lon = lon;
//        this.unixTimestamp = System.currentTimeMillis() / 1000L; // generate current timestamp
//    }

    public EULocation(double lat, double lon, long timestamp) {
        this.lat = lat;
        this.lon = lon;
        this.unixTimestamp = timestamp;
    }

    public EULocation(String license, double lat, double lon) {
        this.license = license;
        this.lat = lat;
        this.lon = lon;
        this.unixTimestamp = System.currentTimeMillis() / 1000L; // generate current timestamp
    }

//    public EULocation(double lat, double lon, long unixTimestamp) {
//        this.lat = lat;
//        this.lon = lon;
//        this.unixTimestamp = unixTimestamp;
//    }

    public EULocation(String license, double lat, double lon, long unixTimestamp) {
        this.license = license;
        this.lat = lat;
        this.lon = lon;
        this.unixTimestamp = unixTimestamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public void setUnixTimestamp(long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public int compareTo(EULocation o) {
        return Long.compare(this.unixTimestamp, o.unixTimestamp);
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", unixTimestamp=" + unixTimestamp +
                '}';
    }
}
