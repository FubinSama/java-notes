package com.wfb.learn.framework.ioc;

import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *  IoC生命周期扩展示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-16 19:43:55
 */
public class IoCLifeCycleCallback {
    static class MyLifecycle implements Lifecycle {

        // 该组件的运行状态
        private volatile boolean running = false;

        @Override
        public void start() {
            System.out.println("[MyLifeCycle]容器调用start方法。。。");
            running = true;
        }

        @Override
        public void stop() {
            System.out.println("[MyLifeCycle]容器调用stop方法。。。");
            running = false;
        }

        /**
         * 检查该组件是否正在运行：
         * 1. 只有该方法返回false（即该组件未在运行时），start方法才会被执行
         * 2. 只有该方法返回true（即该组件正在运行时），stop方法才会被执行
         */
        @Override
        public boolean isRunning() {
            System.out.println("[MyLifeCycle]容器检查该组件的状态。。。");
            return running;
        }
    }

    static class MySmartLifecycle implements SmartLifecycle {

        private volatile boolean running = false;

        @Override
        public void start() {
            System.out.println("[MySmartLifecycle] start ...");
            running = true;
        }

        @Override
        public void stop() {
            System.out.println("[MySmartLifecycle] stop ...");
            running = false;
        }

        @Override
        public boolean isRunning() {
            return running;
        }

        @Override
        public boolean isAutoStartup() {
            return true;
        }

        @Override
        public void stop(Runnable callback) {
            System.out.println("[MySmartLifecycle] callback stop ...");
            this.stop();
            callback.run(); // 这一步是必需的，且一般放在最后
        }

        @Override
        public int getPhase() {
            // 阶段值：树枝越小，越靠前执行start方法，越靠后执行stop方法。标准组件的阶段值是0
            return 0;
        }
    }

    public static void main(String[] args) {
        // 当容器创建时，会触发所有指明`autoStartup`的`SmartLifecycle`实现的`start`方法（依照`Phase`定义的顺序）
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/ioc-lifecycle-callback.xml");
//        applicationContext.start(); // 必须显示调用容器的start方法，来触发[Lifecycle#start]生命周期
//        applicationContext.stop(); // 必须显示调用容器的stop方法，来触发[Lifecycle#start]生命周期
        // 模拟框架中的停止方法，该方法会触发`DefaultLifecycleProcessor`类的`stop`方法，进而触发其`doStop`方法，
        // 从而调用按`Phase`顺序调用所有的`SmartLifecycle`接口实现的`stop(Runnable)`方法
        applicationContext.close();
        // 该方法会向JVM注册回调，在JVM关闭前，会自动调用上下文的close方法。这是非web应用优雅实现容器关闭的方式
//        applicationContext.registerShutdownHook();
    }
}
