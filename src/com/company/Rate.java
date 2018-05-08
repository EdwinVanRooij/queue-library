package com.company;

public class Rate {

    private long timestamp;
    private int rate;
    private int distance;

    /**
     * Specifies a part of a trip.
     *
     * @param timestamp unix timestamp
     * @param rate      price per kilometer
     * @param distance  meters driven on this rate
     */
    public Rate(long timestamp, int rate, int distance) {
        this.timestamp = timestamp;
        this.rate = rate;
        this.distance = distance;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
