package com.wfb.learn.concurrency.P1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @className: ConcurrencyTest
 * @description: 并发操作不一定比串行执行要快，这是因为线程有创建和上下文切换的开销
 * @author: wfb
 * @date: 2021-08-21 15:19:42
 * @version: 1.0
 */
public class ConcurrencyTest {

    private final long count;

    public ConcurrencyTest(int count) {
        this.count = count;
    }

    private void concurrency() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
            return a;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b --;
        }
        thread.join();
        int a = futureTask.get();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency: " + time + "ms, b=" + b + ",a=" + a);
    }

    private void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b=" + b + ",a=" + a);
    }

    /*
    当前的循环次数是：10000
    concurrency: 63ms, b=-10000,a=50000
    serial: 1ms, b=-10000,a=50000
    当前的循环次数是：100000
    concurrency: 2ms, b=-100000,a=500000
    serial: 6ms, b=-100000,a=500000
    当前的循环次数是：1000000
    concurrency: 6ms, b=-1000000,a=5000000
    serial: 5ms, b=-1000000,a=5000000
    当前的循环次数是：10000000
    concurrency: 23ms, b=-10000000,a=50000000
    serial: 33ms, b=-10000000,a=50000000
    当前的循环次数是：100000000
    concurrency: 65ms, b=-100000000,a=500000000
    serial: 88ms, b=-100000000,a=500000000
    当前的循环次数是：1000000000
    concurrency: 610ms, b=-1000000000,a=705032704
    serial: 874ms, b=-1000000000,a=705032704
     */
    public static void main(String[] args) throws Exception {
        ConcurrencyTest[] tests = new ConcurrencyTest[6];
        int k = 10_000;
        for (int i = 0; i < tests.length; i++) {
            System.out.println("当前的循环次数是：" + k);
            tests[i] = new ConcurrencyTest(k);
            tests[i].concurrency();
            tests[i].serial();
            k *= 10;
        }
    }
}
