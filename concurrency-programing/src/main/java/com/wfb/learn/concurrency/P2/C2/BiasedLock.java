package com.wfb.learn.concurrency.P2.C2;

import org.openjdk.jol.info.ClassLayout;

/**
 * @className: BiasedLock
 * @description: 偏向锁
 * @author: wfb
 * @date: 2021-08-22 18:51:14
 * @version: 1.0
 */
public class BiasedLock {

    public static void main(String[] args) throws InterruptedException {
        BiasedLock biasedLock1 = new BiasedLock();
        System.out.println(ClassLayout.parseInstance(biasedLock1).toPrintable());

        Thread.sleep(3_000);

        BiasedLock biasedLock2 = new BiasedLock();
        System.out.println(ClassLayout.parseInstance(biasedLock2).toPrintable());
    }

}
