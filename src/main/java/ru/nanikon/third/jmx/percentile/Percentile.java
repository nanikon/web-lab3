package ru.nanikon.third.jmx.percentile;

/**
 * @author Natalia Nikonova
 */
public class Percentile implements PercentileMBean {
    private double value = 0;

    @Override
    public double getValue() {
        return value;
    }

    public void update(int allCount, int error) {
        this.value = (float) error / allCount * 100;
    }
}
