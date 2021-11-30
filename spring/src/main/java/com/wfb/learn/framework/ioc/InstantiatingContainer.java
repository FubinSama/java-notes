package com.wfb.learn.framework.ioc;

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * <p>
 *  基本XML定义实例化容器并获取Bean
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-09-29 18:30:52
 */
public class InstantiatingContainer {
    public static void main(String[] args) {
        // 基于XML定义配置元数据，从多个配置文件中创建应用上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/services.xml", "com/wfb/learn/framework/ioc/daos.xml");
        PetStoreService petStore = context.getBean("petStore", PetStoreService.class);
        System.out.println(petStore);

        // 基于XML定义配置元数据，从单个配置文件（该文件通过import导入多个配置元数据文件）中创建应用上下文
        ApplicationContext context1 = new ClassPathXmlApplicationContext("com/wfb/learn/framework/ioc/themeSource.xml");
        PetStoreService petStore1 = context1.getBean("petStore", PetStoreService.class);
        System.out.println(petStore1);

        // 基于Groovy定义配置元数据
        GenericGroovyApplicationContext context2 = new GenericGroovyApplicationContext("com/wfb/learn/framework/ioc/themeSource.groovy");
        PetStoreService petStore2 = context2.getBean("petStore", PetStoreService.class);
        System.out.println(petStore2);

        System.out.println(petStore == petStore1);
        System.out.println(petStore == petStore2);

        // 最灵活的变体是`GenericApplicationContext`与读取器委托的结合 — 例如，与`XML`文件的`XmlBeanDefinitionReader`结合使用。
        GenericApplicationContext context3 = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context3).loadBeanDefinitions("com/wfb/learn/framework/ioc/services.xml", "com/wfb/learn/framework/ioc/daos.xml");
        context3.refresh();
        PetStoreService petStore3 = context3.getBean("petStore", PetStoreService.class);
        System.out.println(petStore3);

        GenericApplicationContext context4 = new GenericApplicationContext();
        new GroovyBeanDefinitionReader(context4).loadBeanDefinitions("com/wfb/learn/framework/ioc/themeSource.groovy");
        context4.refresh();
        PetStoreService petStore4 = context4.getBean("petStore", PetStoreService.class);
        System.out.println(petStore4);

        // 您可以在同一个 ApplicationContext 上混合和匹配此类读取器委托，从不同的配置源读取 bean 定义。
        // 然后，您可以使用 getBean 来检索 bean 的实例。 ApplicationContext 接口有一些其他方法来检索 bean，但理想情况下，您的应用程序代码永远不应该使用它们。
        // 实际上，您的应用程序代码根本不应该调用 getBean() 方法，因此完全不依赖 Spring API。
        // 例如，Spring 与 Web 框架的集成为各种 Web 框架组件（例如控制器和 JSF 管理的 bean）提供了依赖注入，让您可以通过元数据（例如自动装配注释）声明对特定 bean 的依赖。
    }
}
