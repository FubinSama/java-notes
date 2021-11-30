package com.wfb.learn.framework.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  自定义Bean作用域示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-13 22:29:09
 */
public class CustomBeanScope {

    static class Thing1 {
        private Thing2 thing2;

        public Thing2 getThing2() {
            return thing2;
        }

        public void setThing2(Thing2 thing2) {
            this.thing2 = thing2;
        }
    }

    static class Thing2 {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/custom-bean-scope.xml");
        Map<String, Map<String, Object>> map = new ConcurrentHashMap<>(4);
        Runnable runnable = () -> {
            Map<String, Object> map1 = new HashMap<>(4);
            Thing1 thing1 = applicationContext.getBean("thing1", Thing1.class);
            map1.put("thing1", thing1);
            map1.put("thing20", thing1.getThing2());
            map1.put("thing21", thing1.getThing2());
            map.put(Thread.currentThread().getName(), map1);
        };
        Thread thread1 = new Thread(runnable, "Thread1");
        Thread thread2 = new Thread(runnable, "Thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Map<String, Object> thread1Map = map.get("Thread1");
        Map<String, Object> thread2Map = map.get("Thread2");
        System.out.println("Thread1和Thread2得到的thing1对象" + boolean2Str(thread1Map.get("thing1") == thread2Map.get("thing1"))); // 说明thing1是单例的
        System.out.println("Thread1和Thread2得到的thing2代理对象" + boolean2Str(thread1Map.get("thing20") == thread2Map.get("thing20"))); // 说明全局的thing1持有同一个thing2代理对象
        System.out.println("Thread1从thing1对象中取得的两个thing2代理对象" + boolean2Str(thread1Map.get("thing20") == thread1Map.get("thing21"))); // 说明取两次thing2代理对象得到的还是同一个
        // 说明代理对象的所有方法调用实际上都是先调用`beanFactory.getBean(targetBeanName)`从容器里得到的目标`bean`后，再调用目标`bean`的同名方法
        System.out.println("Thread1从thing1对象中取得的两个thing2代理对象调用toString方法的结果" + boolean2Str(Objects.equals(thread1Map.get("thing20").toString(), thread1Map.get("thing21").toString())));
    }

    private static String boolean2Str(Boolean bool) {
        if (bool) return "【相等】";
        return "【不相等】";
    }

}
