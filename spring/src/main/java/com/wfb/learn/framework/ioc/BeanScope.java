package com.wfb.learn.framework.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

/**
 * <p>
 *  bean的作用域示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-05 23:49:58
 */
public class BeanScope {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/bean-scope.xml");;
        // 单例bean
        BeanA testA00 = context.getBean("testA0", BeanA.class);
        BeanA testA01 = context.getBean("testA0", BeanA.class);
        System.out.println("单例bean，两次获得的对象" + boolean2Str(testA00 == testA01));
        // 原型bean
        BeanA testA10 = context.getBean("testA1", BeanA.class);
        BeanA testA11 = context.getBean("testA1", BeanA.class);
        System.out.println("原型bean，两次获得的对象" + boolean2Str(testA10 == testA11));
        // 单例beanA依赖于单例beanB
        BeanA testA0B00 = context.getBean("testA0B0", BeanA.class);
        BeanA testA0B01 = context.getBean("testA0B0", BeanA.class);
        System.out.println("单例beanA依赖于单例beanB，两次获得的beanB对象" + boolean2Str(testA0B00.getBeanB() == testA0B01.getBeanB()));

        // 单例beanA依赖于原型beanB
        BeanA testA0B10 = context.getBean("testA0B1", BeanA.class);
        BeanA testA0B11 = context.getBean("testA0B1", BeanA.class);
        System.out.println("单例beanA依赖于原型beanB，两次获得的beanB对象" + boolean2Str(testA0B10.getBeanB() == testA0B11.getBeanB()));

        // 原型beanA依赖于单例beanB
        BeanA testA1B00 = context.getBean("testA1B0", BeanA.class);
        BeanA testA1B01 = context.getBean("testA1B0", BeanA.class);
        System.out.println("原型beanA依赖于单例beanB，两次获得的beanB对象" + boolean2Str(testA1B00.getBeanB() == testA1B01.getBeanB()));

        // 原型beanA依赖于原型beanB
        BeanA testA1B10 = context.getBean("testA1B1", BeanA.class);
        BeanA testA1B11 = context.getBean("testA1B1", BeanA.class);
        System.out.println("原型beanA依赖于原型beanB，两次获得的beanB对象" + boolean2Str(testA1B10.getBeanB() == testA1B11.getBeanB()));

        // 单例beanA依赖于原型beanB：使用AOP作用域代理
        BeanA testA0B20 = context.getBean("testA0B2", BeanA.class);
        BeanA testA0B21 = context.getBean("testA0B2", BeanA.class);
        // 这个是相等的，说明注入的是同一个代理对象
        System.out.println("单例beanA依赖于原型beanB（使用AOP作用域代理），两次获得的beanB的代理对象" + boolean2Str(testA0B20.getBeanB() == testA0B21.getBeanB()));
        // 调用test方法，看看代理对象是否在beanB方法每次调用时创建了新的对象
        System.out.println("单例beanA依赖于原型beanB（使用AOP作用域代理），两次获取beanB的代理对象，并调用toString方法" + boolean2Str(Objects.equals(testA0B20.beanB.toString(), testA0B20.beanB.toString())));
        testA0B20.test();
        testA0B20.test();
        // 可以看到输出中，是两个BeanB对象，这说明BeanB的代理对象在执行`test`方法时，先执行了一个类似于`getBean("targetBeanName.testB2")`的操作，
        // 然后在该操作返回的BeanB对象上调用的`test`方法，这样就保证了`targetBeanName.testB2`的作用域
    }

    private static String boolean2Str(boolean test) {
        if (test) return "【相等】";
        return "【不相等】";
    }

    static class BeanA {
        private BeanB beanB;

        public BeanB getBeanB() {
            return beanB;
        }

        public void setBeanB(BeanB beanB) {
            this.beanB = beanB;
        }

        public void test() {
            System.out.println("BeanA尝试调用BeanB的test方法");
            this.beanB.test();
        }
    }

    static class BeanB {
        public void test() {
            System.out.println(this + "执行BeanB的test方法");
        }
    }

}
