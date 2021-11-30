package com.wfb.learn.concurrency.P2.C3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: CASExample
 * @description: 基于CAS的线程安全计数器和非线程安全计数器
 * @author: wfb
 * @date: 2021-08-26 19:52:02
 * @version: 1.0
 */
public class CASExample {

    public static void main(String[] args) {
        new CASTest().test(); // CASTest总用时: 202ms, 其最终计数结果为：1000000
        new NotSafeTest().test(); // NotSafeTest总用时: 62ms, 其最终计数结果为：931792
    }

    static class CASTest extends Test {
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        public CASTest() { super("CASTest"); }

        public int getCount() { return atomicInteger.get(); }

        public void count() {
            while (true) {
                int i = atomicInteger.get();
                boolean suc = atomicInteger.compareAndSet(i, ++i);
                if (suc) break;
            }
        }
    }

    static class NotSafeTest extends Test {
        private int i = 0;

        public NotSafeTest() { super("NotSafeTest"); }

        public int getCount() { return i; }

        public void count() {
            i++;
        }
    }

    abstract static class Test {

        // 测试实例名
        protected String name;

        public Test(String name) { this.name = name; }


        // 计数
        public abstract void count();

        // 获取计数值
        public abstract int getCount();

        public void test() {
            // 启动了100个线程，每个线程执行10_000次count操作，test方法总计会执行1_000_000次count操作
            List<Thread> threads = Stream.generate(() -> new Thread(() -> {
                for (int i = 0; i < 10_000; i++) {
                    count();
                }
            })).limit(100).collect(Collectors.toList());
            long start = System.currentTimeMillis();
            threads.forEach(Thread::start);
            threads.forEach(it -> {
                try {
                    it.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            long time = System.currentTimeMillis() - start;
            System.out.println(name + "总用时: " + time + "ms, 其最终计数结果为：" + getCount());
        }
    }
}
