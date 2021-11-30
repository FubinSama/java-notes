package com.wfb.learn.concurrency.P1;

/**
 * @className: DeadlockDemo
 * @description: 死锁
 * @author: wfb
 * @date: 2021-08-21 16:33:42
 * @version: 1.0
 */
public class DeadlockDemo {
    private static final String A = "A";
    private static final String B = "B";

    private void deadLock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("2");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("t1和t2执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        new DeadlockDemo().deadLock();
    }
    
}
