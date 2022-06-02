package ru.nanikon.third.jmx.counter;

import ru.nanikon.third.entity.ShotEntity;

/**
 * @author Natalia Nikonova
 */
public interface CounterMBean {
    int getAllShot();
    int getError();
}
