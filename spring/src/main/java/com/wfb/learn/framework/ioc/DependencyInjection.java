package com.wfb.learn.framework.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  依赖注入示例
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-02 19:58:37
 */
public class DependencyInjection {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/dependency-injection.xml");;
        // 构造函数依赖注入
        PetStoreService test10 = context.getBean("test10", PetStoreService.class);
        System.out.println(test10);
        Test2 test11 = context.getBean("test11", Test2.class);
        System.out.println(test11);
        // 静态工厂方法依赖注入
        PetStoreService test20 = context.getBean("test20", PetStoreService.class);
        System.out.println(test20);
        Test2 test21 = context.getBean("test21", Test2.class);
        System.out.println(test21);
        // 实例工厂方法依赖注入
        PetStoreService test30 = context.getBean("test30", PetStoreService.class);
        System.out.println(test30);
        Test2 test31 = context.getBean("test31", Test2.class);
        System.out.println(test31);
        // setter依赖注入
        PetStoreService test40 = context.getBean("test40", PetStoreService.class);
        System.out.println(test40);
        Test2 test41 = context.getBean("test41", Test2.class);
        System.out.println(test41);

        System.out.println("-----------------------------下面是依赖注入各种不同值的示例-------------------------------------------");
        DependencyInjectionTest dependencyInjectionTest00 = context.getBean("dependencyInjectionTest00", DependencyInjectionTest.class);
        System.out.println(dependencyInjectionTest00);
        DependencyInjectionTest dependencyInjectionTest01 = context.getBean("dependencyInjectionTest01", DependencyInjectionTest.class);
        System.out.println(dependencyInjectionTest01);
        DependencyInjectionTest dependencyInjectionTest10 = context.getBean("dependencyInjectionTest10", DependencyInjectionTest.class);
        System.out.println(dependencyInjectionTest10);
        DependencyInjectionTest dependencyInjectionTest11 = context.getBean("dependencyInjectionTest11", DependencyInjectionTest.class);
        System.out.println(dependencyInjectionTest11);
        DependencyInjectionTest child1 = context.getBean("child1", DependencyInjectionTest.class);
        System.out.println(child1);
        DependencyInjectionTest child2 = context.getBean("child2", DependencyInjectionTest.class);
        System.out.println(child2);

        System.out.println("--------------------------------------下面是DependsOn示例-------------------------------------------------------------");
        DependsOn0 dependsOn0 = context.getBean("dependsOn0", DependsOn0.class);
        System.out.println(dependsOn0);
    }

    static class DependencyInjectionTest {
        private Integer primitive;
        private String idref;
        private Test bean;
        private Test innerBean;
        private List list;
        private Map<String, String> genericMap;
        private String nullOrEmptyStr;

        public DependencyInjectionTest() {}

        public DependencyInjectionTest(Integer primitive, String idref, Test bean, Test innerBean, List list, Map<String, String> genericMap, String nullOrEmptyStr) {
            this.primitive = primitive;
            this.idref = idref;
            this.bean = bean;
            this.innerBean = innerBean;
            this.list = list;
            this.genericMap = genericMap;
            this.nullOrEmptyStr = nullOrEmptyStr;
        }

        public Integer getPrimitive() {
            return primitive;
        }

        public void setPrimitive(Integer primitive) {
            this.primitive = primitive;
        }

        public String getIdref() {
            return idref;
        }

        public void setIdref(String idref) {
            this.idref = idref;
        }

        public Test getBean() {
            return bean;
        }

        public void setBean(Test bean) {
            this.bean = bean;
        }

        public Test getInnerBean() {
            return innerBean;
        }

        public void setInnerBean(Test innerBean) {
            this.innerBean = innerBean;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public Map<String, String> getGenericMap() {
            return genericMap;
        }

        public void setGenericMap(Map<String, String> genericMap) {
            this.genericMap = genericMap;
        }

        public String getNullOrEmptyStr() {
            return nullOrEmptyStr;
        }

        public void setNullOrEmptyStr(String nullOrEmptyStr) {
            this.nullOrEmptyStr = nullOrEmptyStr;
        }

        @Override
        public String toString() {
            return "DependencyInjectionTest{" +
                    "primitive=" + primitive +
                    ", idref='" + idref + '\'' +
                    ", bean=" + bean +
                    ", innerBean=" + innerBean +
                    ", list=" + list +
                    ", genericMap=" + genericMap +
                    ", nullOrEmptyStr='" + nullOrEmptyStr + '\'' +
                    '}';
        }
    }

    static class DependsOn0 {
        public DependsOn0() {
            System.out.println("DependsOn0依赖于其他类，该行应该在别的类的构造器打印之后才打印");
        }
    }

    static class DependsOn1 {
        public DependsOn1() {
            System.out.println("DependsOn1是DependsOn0的依赖项，这行应该在构造DependsOn0之前打印");
        }
    }

    static class DependsOn2 {
        public DependsOn2() {
            System.out.println("DependsOn2是DependsOn0的依赖项，这行应该在构造DependsOn0之前打印");
        }
    }

}
