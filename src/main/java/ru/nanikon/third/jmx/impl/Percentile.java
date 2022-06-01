package ru.nanikon.third.jmx.impl;

import ru.nanikon.third.jmx.inter.PercentileMBean;

/**
 * @author Natalia Nikonova
 */
public class Percentile implements PercentileMBean {
    private double value = 0;

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double newValue) {
        this.value = newValue;
    }
}
