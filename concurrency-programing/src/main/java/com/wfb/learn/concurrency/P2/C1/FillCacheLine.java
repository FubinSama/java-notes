package com.wfb.learn.concurrency.P2.C1;

import sun.misc.Contended;

import java.util.Random;

/**
 * @className: FillCacheLine
 * @description: 探究通过追加无用的字节来填充缓存行，是两个元素位于不同缓冲行，从而对效率的影响
 * 使用了java8引入的缓存行填充注解@Contended，需要在VM参数中加入`-XX:-RestrictContended`选项来激活
 * @author: wfb
 * @date: 2021-08-22 13:41:03
 * @version: 1.0
 */
public class FillCacheLine {

    private static final int cnt = 1_000_000;

    private static void myPrint(long time1, long time2) {
        String str = "NoPaddingTest time = " + time1 + ", PaddingTest time = " + time2;
        if (time1 > time2) { // 蓝色：表示PaddingTest版本用时比NoPaddingTest版本用时少
            System.out.println("\033[;34m" + str + "\033[0m");
        } else { // 紫色：表示PaddingTest版本用时比NoPaddingTest版本用时多
            System.out.println("\033[;35m" + str + "\033[0m");
        }
    }

    /**
     * 可以看到控制台的打印都是蓝色，说明缓存行填充后比非填充的版本用时更少
     * 这是因为PaddingTest版本的变量a和变量b不在同一个缓存行中，线程tA对变量a的写操作不会导致线程tB中缓存变量b的缓存行失效，
     * 同样线程tB对变量b的写操作也不会导致线程tA中缓存变量a的缓存行失效。程序可以继续使用自己的缓存，从而减少了缓存不命中导致的不命中处罚时间
     * 而NoPaddingTest版本因为变量a和变量b处于同一个缓存行，因为volatile关键字的缓存失效原则，导致了大量的缓存不命中处罚
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<10; i++) {
            long time1 = NoPaddingTest.test();
            long time2 = PaddingTest.test();
            myPrint(time1, time2);
        }
    }

    static class NoPaddingTest {

        private Integer a = 0;
        private Integer b = 0;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public static long test() throws InterruptedException {
            NoPaddingTest noPaddingTest = new NoPaddingTest();
            Thread tA = new Thread(() -> {
                int sum = 0;
                for (int i = 0; i < cnt; i++) {
                    noPaddingTest.setA(new Random().nextInt(10));
                    sum += noPaddingTest.getA();
                }
                System.out.println(Thread.currentThread().getName() + " sum A: "+ sum);
            }, "NoPaddingTest-tA");
            Thread tB = new Thread(() -> {
                int sum = 0;
                for (int i = 0; i < cnt; i++) {
                    noPaddingTest.setB(new Random().nextInt(10));
                    sum += noPaddingTest.getB();
                }
                System.out.println(Thread.currentThread().getName() + " sum B: "+ sum);
            }, "NoPaddingTest-tB");
            long start = System.currentTimeMillis();
            tA.start();
            tB.start();
            tA.join();
            tB.join();
            long time = System.currentTimeMillis() - start;
            System.out.println("NoPaddingTest 总用时为：" + time + "ms");
            return time;
        }
    }

    static class PaddingTest {
        private @Contended Integer a = 0;
        private @Contended Integer b = 0;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public static long test() throws InterruptedException {
            PaddingTest paddingTest = new PaddingTest();
            Thread tA = new Thread(() -> {
                int sum = 0;
                for (int i = 0; i < cnt; i++) {
                    paddingTest.setA(new Random().nextInt(10));
                    sum += paddingTest.getA();
                }
                System.out.println(Thread.currentThread().getName() + " sum A: "+ sum);
            }, "PaddingTest-tA");
            Thread tB = new Thread(() -> {
                int sum = 0;
                for (int i = 0; i < cnt; i++) {
                    paddingTest.setB(new Random().nextInt(10));
                    sum += paddingTest.getB();
                }
                System.out.println(Thread.currentThread().getName() + " sum B: "+ sum);
            }, "PaddingTest-tB");
            long start = System.currentTimeMillis();
            tA.start();
            tB.start();
            tA.join();
            tB.join();
            long time = System.currentTimeMillis() - start;
            System.out.println("PaddingTest 总用时为：" + time + "ms");
            return time;
        }
    }

}
