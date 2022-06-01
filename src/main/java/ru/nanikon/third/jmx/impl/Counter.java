package ru.nanikon.third.jmx.impl;

import ru.nanikon.third.entity.ShotEntity;
import ru.nanikon.third.jmx.inter.CounterMBean;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @author Natalia Nikonova
 */
public class Counter extends NotificationBroadcasterSupport implements CounterMBean {
    private int allShot = 0;
    private int error = 0;
    private long sequenceNumber = 1;

    @Override
    public void increment(ShotEntity shot) {
        allShot++;
        if (shot.isHit()) {
            error++;
        }
        if (allShot % 10 == 0) {
            Notification n =
                    new AttributeChangeNotification(this,
                            sequenceNumber++,
                            System.currentTimeMillis(),
                            "The number of all shots is a multiple of 10",
                            "all shots",
                            "int",
                           allShot - 1,
                            allShot);

            sendNotification(n);
        }
    }

    @Override
    public int getAllShot() {
        return allShot;
    }

    @Override
    public int getError() {
        return error;
    }
}
