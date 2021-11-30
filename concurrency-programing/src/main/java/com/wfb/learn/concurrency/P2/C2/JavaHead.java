package com.wfb.learn.concurrency.P2.C2;

import org.openjdk.jol.info.ClassLayout;

/**
 * @className: JavaHead
 * @description: 查看实例对象的布局，jdk8版本是默认开启指针压缩的。
 * 可以通过VM参数`-XX:-UseCompressedOops`关闭指针压缩
 * @author: wfb
 * @date: 2021-08-22 15:25:39
 * @version: 1.0
 */
public class JavaHead {

    static class L {
        private boolean myBoolean = true;
    }

    public static void main(String[] args) {
        L l = new L();
        //输出 l对象 的布局
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
        // 调用Object的hashCode方法，只有调用默认的hashCode方法，才会写入对象头，自己重载的无效
        System.out.println(l.hashCode());
        //输出 l对象 的布局
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
        // 输出数组的布局
        int[] a = {1, 2, 3, 4};
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

}