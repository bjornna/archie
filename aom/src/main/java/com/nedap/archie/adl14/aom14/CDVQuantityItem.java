package com.nedap.archie.adl14.aom14;

import com.nedap.archie.base.Interval;

public class CDVQuantityItem {

    private String units;
    private Interval<Double> magnitude;
    private Interval<Long> precision;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Interval<Double> getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Interval<Double> magnitude) {
        this.magnitude = magnitude;
    }

    public Interval<Long> getPrecision() {
        return precision;
    }

    public void setPrecision(Interval<Long> precision) {
        this.precision = precision;
    }
}
