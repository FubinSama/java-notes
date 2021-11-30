package com.wfb.learn.framework.ioc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *  对Bean进行实例化示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-02 18:20:20
 */
public class InstantiatingBean {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/instantiating-bean.xml");
        // 使用构造器进行实例化
        Test test1 = applicationContext.getBean("test1", Test.class);
        System.out.println(test1);
        // 使用静态工厂方法进行实例化
        Test test2 = applicationContext.getBean("test2", Test.class);
        System.out.println(test2);
        // 使用实例工厂方法进行实例化
        Test test3 = applicationContext.getBean("test3", Test.class);
        System.out.println(test3);
        // 使用静态工厂方法实例化另一个类型的bean
        Test test4 = applicationContext.getBean("test4", Test.class);
        System.out.println(test4);
        // 实例化bean
        Test test5 = applicationContext.getBean("test5", Test.class);
        System.out.println(test5);

    }

    static class DefaultServiceLocator {
        public Test createTest() {
            return Test.createTest();
        }
    }

    public static Test createTest() {
        return Test.createTest();
    }

    public static class TestFactoryBean implements FactoryBean<Test> {

        @Override
        public Test getObject() throws Exception {
            return Test.createTest();
        }

        @Override
        public Class<?> getObjectType() {
            return Test.class;
        }

        @Override
        public boolean isSingleton() {
            return FactoryBean.super.isSingleton();
        }
    }
}
