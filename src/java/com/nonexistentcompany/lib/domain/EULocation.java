package com.nonexistentcompany.lib.domain;

public class EULocation implements Comparable<EULocation> {
    private double lat;
    private double lng;
    private long timestamp;

    public EULocation(double lat, double lng, long timestamp) {
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
    }

    public EULocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.timestamp = System.currentTimeMillis() / 1000L; // generate current timestamp
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(EULocation o) {
        return Long.compare(this.timestamp, o.timestamp);
    }

    @Override
    public String toString() {
        return "EULocation{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", timestamp=" + timestamp +
                '}';
    }
}
