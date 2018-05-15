package com.nonexistentcompany;

public class Location implements Comparable<Location> {
    private double lat;
    private double lon;
    private long unixTimestamp;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.unixTimestamp = System.currentTimeMillis() / 1000L; // generate current timestamp
    }

    public Location(double lat, double lon, long unixTimestamp) {

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

    @Override
    public int compareTo(Location o) {
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
