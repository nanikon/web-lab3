package ru.nanikon.third.jmx.inter;

import ru.nanikon.third.entity.ShotEntity;

/**
 * @author Natalia Nikonova
 */
public interface CounterMBean {
    void increment(ShotEntity shot);
    int getAllShot();
    int getError();
}
