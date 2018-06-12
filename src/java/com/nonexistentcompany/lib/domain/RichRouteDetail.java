package com.nonexistentcompany.lib.domain;

public class RichRouteDetail {
    private Double rate;
    private String description;
    private Long start;
    private Long end;

    public RichRouteDetail(Double rate, String description, Long start, Long end) {
        this.rate = rate;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
