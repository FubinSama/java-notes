package com.wfb.learn.framework.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *  ApplicationContextAware和BeanNameAware示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-16 23:18:09
 */
public class AwareBean {

    @Bean
    public Test1 test1() {
        return new Test1();
    }

    // 通过实现ApplicationContextAware和BeanNameAware接口，来让Spring容器注入该bean的name和所在的应用上下文
    static class Test1 implements ApplicationContextAware, BeanNameAware {

        private String beanName;
        private ApplicationContext applicationContext;

        @Override
        public void setBeanName(String name) {
            this.beanName = name;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        public void test() {
            System.out.println("该bean的name是：" + this.beanName);
            System.out.println("该bean所在的应用上下文是：" + this.applicationContext);
        }
    }

    @Bean
    public Test2 test2() {
        return new Test2();
    }

    // 通过自动注入注解`@Autowired`来要求Spring容器注入该bean的name和所在的应用上下文，
    // 因为beanName是一个String 的值，在IoC里面太多了，无法直接通过String类型进行自动注入，不清楚有什么黑科技可以直接beanName的自动注入
    static class Test2 {

        private ApplicationContext applicationContext;

        @Autowired
        public void setApplicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public void test() {
            System.out.println("该bean所在的应用上下文是：" + this.applicationContext);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareBean.class);
        Test1 test1 = context.getBean("test1", Test1.class);
        test1.test();
        Test2 test2 = context.getBean("test2", Test2.class);
        test2.test();
    }

}
