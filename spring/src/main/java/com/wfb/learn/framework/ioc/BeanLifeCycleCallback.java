package com.wfb.learn.framework.ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 *  Bean生命周期扩展示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-14 20:45:16
 *
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
 */
public class BeanLifeCycleCallback {


    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public LifeCycleCallback1 test1() {
        return new LifeCycleCallback1();
    }

    static class LifeCycleCallback1 implements InitializingBean, DisposableBean {

        public void test() {
            System.out.println("【test】方法正在被执行");
        }

        public void initMethod() {
            System.out.println("【init-method】方法正在被执行");
        }

        public void destroyMethod() {
            System.out.println("【destroy-method】方法正在被执行");
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("【@PostConstruct】方法正在被执行");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("【@PreDestroy】方法正在被执行");
        }

        @Override
        public void afterPropertiesSet() {
            System.out.println("【InitializingBean#afterPropertiesSet】方法正在被执行");
        }

        @Override
        public void destroy() {
            System.out.println("【DisposableBean#destroy】方法正在被执行");
        }

    }

    /*
    执行结果为：
    【@PostConstruct】方法正在被执行
    【InitializingBean#afterPropertiesSet】方法正在被执行
    【init-method】方法正在被执行
    【test】方法正在被执行
    【@PreDestroy】方法正在被执行
    【DisposableBean#destroy】方法正在被执行
    【destroy-method】方法正在被执行
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleCallback.class);
        LifeCycleCallback1 test1 = applicationContext.getBean("test1", LifeCycleCallback1.class);
        test1.test();
        applicationContext.close();
    }

}
