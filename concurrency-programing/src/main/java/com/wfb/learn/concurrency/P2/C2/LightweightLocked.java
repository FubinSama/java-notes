package com.wfb.learn.concurrency.P2.C2;

import org.openjdk.jol.info.ClassLayout;

/**
 * @className: LightweightLocked
 * @description: 轻量级锁
 * lightweightLocked刚创建出来时是无锁的。之后因为t1线程进入同步块，导致其变为轻量级锁。在t1退出同步块时，因为发现有线程(t2)正在尝试获取锁，导致锁升级为重量级锁
 * @author: wfb
 * @date: 2021-08-22 19:49:11
 * @version: 1.0
 */
public class LightweightLocked {

    public void test() {
        System.out.println("-----------------------" + Thread.currentThread().getName() + " 同步块前-----------------------");
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
        synchronized (this) {
            System.out.println("-----------------------" + Thread.currentThread().getName() + " 同步块中-----------------------");
            System.out.println(ClassLayout.parseInstance(this).toPrintable());
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------------" + Thread.currentThread().getName() + " 同步块后-----------------------");
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
    }

    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(3_000L);
        LightweightLocked lightweightLocked = new LightweightLocked();
        System.out.println(ClassLayout.parseInstance(lightweightLocked).toPrintable());
        Thread t1 = new Thread(lightweightLocked::test, "t1");
        Thread t2 = new Thread(lightweightLocked::test, "t2");
        t1.start();
        Thread.sleep(100L);
        t2.start();
        t1.join();
        t2.join();
    }

}
