package com.wfb.learn.framework.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *  Bean定义的继承性示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-17 00:33:15
 */
public class BeanDefinitionInheritance {

    static class Parent {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Parent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Child {
        private String name;
        private String text;

        public void setName(String name) {
            this.name = name;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "name='" + name + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/bean-definition-inheritance.xml");
        Parent parent = context.getBean("parent", Parent.class);
        System.out.println(parent);
        Child child = context.getBean("child", Child.class);
        System.out.println(child);
        Child child2 = context.getBean("child2", Child.class);
        System.out.println(child2);
    }

}
