# Spring的IoC

> **IoC(控制反转)**也被成为**DI(依赖注入)**：它是一个过程，对象仅通过*构造函数参数*、*工厂方法的参数*或者实例创建后调用对应的*setter方法*来定义依赖。而容器会在创建bean时注入这些依赖项。这个过程基本上是`bean`本身的逆过程（因此得名，控制反转），通过使用类的直接构造或服务定位器模式自行控制其依赖项的实例化和进行依赖的定位。

`org.springframework.beans`和`org.springframework.context`包是主要的`Spring IoC`容器。
`BeanFactory`接口提供了管理任何类型对象的高级配置机制，而其子接口`ApplicationContext`提供了更多的功能：

1. 更容易集成AOP特性
2. 消息资源处理（用于国际化）
3. 事件发布
4. 应用层特定上下文，例如用于`Web`应用程序的`WebApplicationContext`。

总的来说：`BeanFactory`提供了配置框架和基本功能，`ApplicationContext`增加了更多企业级应用特定的功能，它是`BeanFactory`完全的超集。

在Spring中，构成应用程序主干，并由`Spirng IoC`容器管理的对象被称为`Bean`。它由`Spring IoC`容器进行实例化、组成和管理。它和它的依赖的关系被定义在容器的配置元信息中。

## 容器总览

> 容器通过读取配置元数据来获取有关要实例化、配置和组装哪些对象的指令。配置元数据可以以`XML`、`Java注解`或`Java代码`的方式来描述。从而表达组成应用程序的对象以及这些对象之间丰富的相互依赖关系。

Spring 提供了`ApplicationContext`接口的几个实现。在单机应用中，通常创建`ClassPathXmlApplicationContext`或`FileSystemXmlApplicationContext`的实例。虽然`XML`一直是定义配置元数据的传统格式，但可以通过提供少量`XML`配置来声明性地启用对这些附加元数据格式的支持，从而指示容器使用`Java注解`或`Java代码`来描述配置元数据。

在大多数应用场景中，不需要显式的用户代码来实例化一个或多个`Spring IoC`容器实例。例如，在`Web`应用程序场景中，应用程序的`web.xml`文件中简单的八行（左右）的样板XML配置通常就足够了。

### 配置元数据

`Spring IoC`容器通过解析配置元数据来获取如何`实例化`、`配置`和`组装`应用程序中的对象。

配置元数据传统上以简单直观的`XML`格式提供。但是基于`XML`的元数据并不是唯一的格式。`Spring IoC`容器本身与实际配置元数据的格式完全分离。现在，许多开发人员为他们的`Spring`应用程序选择基于`Java`的配置。

其基本的定义格式见[configuration-metadata.xml](configuration-metadata.xml)

### 容器的使用

`ApplicationContext`能够维护不同`bean`及其依赖项的注册表。通过使用方法`T getBean(String name, Class<T> requiredType)`，可以检索`bean`的实例。

详见[InstantiatingContainer.java](InstantiatingContainer.java)

## Bean总览

`Spring IoC`容器管理一个或多个`bean`。这些`bean`是容器使用配置元数据（例如，在XML中以`<bean/>`定义）创建的。

在容器本身内，这些`bean`定义表示为`BeanDefinition`对象，它通常包含以下信息：

1. **包限定的类名**：通常是定义的`bean`的实际实现类。
2. **Bean行为配置元素**：它说明`Bean`在容器中的行为方式（范围、生命周期回调等）。
3. **对bean执行其工作所需的其他bean的引用**：这些引用也称为协作者或依赖项。
4. **要在新创建的对象中设置的其他配置设置**：如，池的大小限制或在管理连接池的`bean`中使用的连接数。

这些元数据被转换为组成每个`BeanDefinition`(即`org.springframework.beans.factory.config.BeanDefinition`的实现类)的一组属性。包括：

1. `Class`：`BeanDefinition#getBeanClassName`，其基础实现在`AbstractBeanDefinition`抽象类中
2. `Name`：`BeanDefinitionHolder#getBeanName` ,`BeanDefinitionHolder`类对象中存储了`beanDefinition`、`beanName`和`aliases`三部分信息。
3. `Scope`：`BeanDefinition#getScope`，其基础实现在`AbstractBeanDefinition`抽象类中
4. `Constructor arguments`：`BeanDefinition#getConstructorArgumentValues`，其基础实现在`AbstractBeanDefinition`抽象类中
5. `Properties`：`BeanDefinition#getPropertyValues`，其基础实现在`AbstractBeanDefinition`抽象类中
6. `Autowiring mode`：`AbstractBeanDefinition#getAutowireMode`，其基础实现在`AbstractBeanDefinition`抽象类中
7. `Lazy initialization mode`：`BeanDefinition#isLazyInit`，其基础实现在`AbstractBeanDefinition`抽象类中
8. `Initialization method`：`BeanDefinition#getInitMethodName`，其基础实现在`AbstractBeanDefinition`抽象类中
9. `Destruction method`：`BeanDefinition#getDestroyMethodName`，其基础实现在`AbstractBeanDefinition`抽象类中

`BeanDefinitionRegistry#registerBeanDefinition`接口用来将BD注册到容器中，其基础实现在`DefaultListableBeanFactory`。另外，该类中也含有三级缓存的逻辑

除了包含有通过解析配置元数据中定义的`BeanDefinition`来创建的`Bean`外，`ApplicationContext`的实现类还允许注册那些在容器外部（由用户创建）创建的对象。

通过调用`ConfigurableApplicationContext`接口（其基本的实现在`AbstractApplicationContext`抽象类中）的`getBean()`方法来获取`ConfigurableListableBeanFactory`，从而调用其继承的`SingletonBeanRegistry`中的`registerSingleton`方法
或调用`BeanDefinitionRegistry`接口（`ConfigurableListableBeanFactory`的默认实现`DefaultListableBeanFactory`类继承`BeanDefinitionRegistry`接口）中定义的`registerBeanDefinition`方法来注册。

`Bean`元数据和手动提供的单例对象需要尽早注册，以便容器在自动装配和其他内省步骤中正确推理它们。虽然在某种程度上支持覆盖现有元数据和对象实例，但官方不支持在运行时注册新`bean`（并发地对工厂进行实时访问），这可能会导致并发访问异常或不一致的`bean`容器状态。

### Bean的命名

每个`bean`都有一个或多个标识符。这些标识符在承载`bean`的容器中必须是唯一的。一个`bean`通常只有一个标识符，如果它需要多个，则可以将其它的视为别名。

在XML中使用`id`属性定义`bean`的标识符，使用`name`属性定义别名（多个时用‘,’、‘;’或空格分隔，）。通常，这些标识符是字母数字的组合（如'myBean'、'someService' 等），但它们也可以包含特殊字符。

当未显示定义`bean`的`id`或`name`时，容器将为该`bean`生成一个唯一的标识符。 但是，如果要通过名称引用该`bean`，通过使用`ref `属性或服务定位模式（`Service Locator`）进行查找时，则必须提供名称。

> `Bean`命名规范：在命名`bean`时对实例字段名称使用标准`Java`规范。也就是说，`bean`名称以小写字母开头，并使用驼峰命名法(如： `accountManager`、`accountService`、`userDao`、`loginController`等）。采用统一的格式命名`bean`使配置更易于阅读和理解。此外，如果使用`Spring AOP`，这时想要则在将切片应用于一组名称相关的`bean`时会很有帮助。

通过类路径中的组件扫描，`Spring`为未命名的组件生成`bean`名称，遵循前面描述的规范：本质上，采用简单的类名并将其初始字符转换为小写。但是，在有多个字符且第一个和第二个字符都是大写的（不寻常的）特殊情况下，原始大小写被保留。其规则与`java.beans.Introspector.decapitalize`（Spring实际上就是调用的这个，在调用`AnnotatedBeanDefinitionReader`的`doRegisterBean`方法时，对于没有在注解中声明标识符的，就会调用`AnnotationBeanNameGenerator#generateBeanName`方法来创建一个`beanName`,其核心代码为`Introspector.decapitalize(shortClassName)`）定义的规则相同。

也可以在`<bean>`定义外为`bean`定义别名，其基本语法为`<alias name="fromName" alias="toName"/>`。

### Bean的实例化

`Bean Definition`本质上是创建一个或多个对象的配方。当容器被询问时，会查看该命名`bean`的相关配方，并使用该配置元数据来创建（或获取）实际对象。

如果使用基于`XML`的配置元数据，则指定要在`<bean/>`元素的`class`属性中实例化的对象的类型。这个类属性（在内部，它是`BeanDefinition``实例上的`Class`属性）通常是强制性的。

> 匿名类的命名：如果有一个在包`com.example`下有一个类`SomeThing`，该类有一个静态内部类`OtherThing`。应该使用`$`和`.`来分隔，即其表示为：`com.example.SomeThing$OtherThing`或者`com.example.SomeThing.OtherThing`。

`Spring IoC`容器几乎可以管理您希望它管理的任何类。它不仅限于管理真正的`JavaBean`。大多数`Spring`用户更喜欢实际的`JavaBeans`，它只有一个默认（无参数）构造函数和适当的 `setter`和`getter`，它们以容器中的属性为模型。您还可以在您的容器中拥有非`bean`风格的类。例如，如果您需要使用绝对不符合`JavaBean`规范的遗留连接池，`Spring`也可以管理它。

可以通过三种方式对bean进行实例化：

1. 使用构造器实例化
2. 使用静态工厂方法实例化
3. 使用实例工厂方法实例化

详见[InstantiatingBean.java](InstantiatingBean.java)的`test1`、`test2`、`test3`

决定`bean`的运行时类型并不是一件容易的事。在`bean`的元数据定义中指定的类型只是一个初始的类型引用，然而当其定义中声明了使用静态工厂方法实例化或者它是一个`FactoryBean`类（实现了该接口），或者使用实例工厂方法实例化时，它的实际类型都有可能不同。（详见：[InstantiatingBean.java](InstantiatingBean.java)的`test3`、`test4`、`test5`）
另外，当基于接口实现AOP时，代理可能会包装`bean`实例，并限制暴露目标`bean`的实际类型。

找出特定`bean`的实际运行时类型的推荐方法是通过`bean`的名称调用`BeanFactory#getType`方法。这考虑了上述所有情况，实际上这就是通过`bean`名称调用`BeanFactory#getBean`方法得到的`bean`的类型。

## 依赖

一个典型的企业级应用不可能只由简单的对象（在`Spring`的术语中叫做`bean`）组成。即使最简单的应用也需要几个对象协同过做以呈现最终用户看到的连贯的应用程序。

### 依赖注入

> **依赖注入**是一个过程：对象仅通过*构造函数参数*、*工厂方法的参数*或者实例创建后调用对应的*setter方法*来定义依赖。而容器会在创建bean时注入这些依赖项。

通过`DI`原则，代码变得更加清晰，使得对象和其依赖之间的解耦更有效。对象不再需要查找它们的依赖，也不需要知道依赖的位置或实际类型。因此，类变得更容易测试。当依赖项基于接口或抽象类时，可以很简单的使用`stub`和`mock`实现来进行单元测试。

`ApplicationContext`及其子类支持基于构造函数和基于`setter`的依赖注入。我们可以在已经通过构造函数方法注入了一些依赖项之后，再基于`setter`进行其他依赖项的注入。Spring是以`BeanDefinition`实例的形式配置的依赖项，它可以与`PropertyEditor`实例（如`StringArrayPropertyEditor`可以将CSV格式的字符串转化为字符串数组）结合使用来将属性从一种格式转换为另一种格式。实际上，大多数`Spring`用户并不直接（即以编程方式）使用这些类，而是使用`XML`类型的`bean`定义、带注解的组件（即用`@Component`、`@Controller`等注解类）或在`@Configuration`注解的类的方法上添加`@Bean`注解。然后让内部转换为`BeanDefinition`的实例，并加载到`Spring IoC`容器实例。

虽然可以混用基于构造器的依赖注入和基于`setter`器的依赖注入。但是以经验来看，对于强制依赖项（当使用默认值时，整个服务就没法运行了）最好使用构造函数，而对于可选依赖项（当使用默认值时可能只会影响部分功能或者没啥影响）最好使用setter方法。虽然，可以通过在setter方法上注解`@Required`可以使其变为一个强制依赖，但实际上还是使用构造参数更好。
`Spring`团队通常提倡构造函数注入，因为这样很方便我们使用不可变模式来实现我们的应用组件，而且它也确保了必须依赖项不会为`NULL`。此外，构造函数注入的组件总是以完全初始化的状态返回给客户端（调用方）。BTW：一个类含有大量构造参数，意味着该类承担了太多的职责，这样的代码是糟糕的，应该重构并进行职责分离。
`Setter`器依赖注入应该主要用在可选依赖（这些依赖有合适地默认值）上。否则，必须在代码使用依赖项的任何地方执行非空检查。`setter`注入的一个好处是使该类的对象可以在以后重新配置或重新注入。因此，通过`JMX MBean`进行管理是`setter`注入的一个合适地使用场景。
使用何种依赖注入形式也取决于使用依赖注入场景。有时，当我们使用某些不公开源码的第三方类时，我们只能使用他们定义的依赖注入形式。比如，他们只暴露了无参构造函数和`setter`方法，那么也就只能使用`setter`器来进行依赖注入了。

### 依赖注入的解析流程

容器实现依赖注入解析如下所示：

1. 使用描述所有`bean`的配置元数据（可以是`XML`、显式的`Java`代码或注解）创建和初始化`ApplicationContext`实例
2. 对于每个`bean`，它的依赖以属性、构造函数参数、静态工厂方法参数、实例化工厂方法参数等形式表示。当`bean`被实际创建时，这些依赖将由容器提供给`bean`
3. 每个属性或构造函数参数都是要设置的值的实际定义，或者是对容器中另一个`bean`的引用。
4. 作为值的每个属性或构造函数参数都从其指定格式转换为该属性或构造函数参数的实际类型。默认情况下，`Spring`可以将字符串格式提供的值转换为所有内置类型，例如`int`、`long`、`String`、`boolean`等。

`Spring`容器会在容器被容器被创建时验证每个`bean`的配置。然而，`bean`的属性直到实际创建才会被注入。`singleton`作用域并且设置为预实例化的`bean`（这是默认值）会在容器初始化时一并被创建。其他的`bean`只有在第一次获取时才会被创建。创建`bean`时会因为其依赖和依赖的依赖导致一个复杂的创建和赋值图。第一次获取时才创建`bean`的依赖解析机制使得依赖项解析不匹配延迟显示，它们可能只有在第一次请求有影响的`bean`时才出现。

**循环依赖**（A依赖于B,同时B依赖于A。或者更复杂的递归依赖）：如果主要使用构造函数注入，则更容易遇到无法解决的循环依赖场景。比如，类A需要通过构造器注入类B的实例，而类B需要通过构造器注入类A的实例。`Spring IoC`容器在运行时会检测到这个循环引用，并抛出一个`BeanCurrentlyInCreationException`异常。对于这种情况一种可能的解决方案是修改源代码将某些类改为使用`setter`方法注入。更极端的方式是只使用`setter`方法注入，`Spring IoC`容器通过三级缓存基本可以解决默认情况（即`singleton`作用域且预实例化，无AOP增强）下完全基于`setter`注入的`bean`之间的循环依赖问题。`bean A`和`bean B`之间的循环依赖迫使其中一个`bean`在完全初始化之前注入另一个`bean`（经典的鸡和蛋场景）。

通常可以相信`Spring`会做正确的事情。它会在容器加载时检测到配置错误（比如：依赖注入指向了一个不存在的`bean`、发生了循环依赖）。`Spring`会尽可能晚的解析和注入依赖（直到实际需要创建`bean`才进行这些操作）。这意味着就算`Spring`正确的加载，当接收到一个对有问题的对象（无法创建其某个依赖）的请求时，它仍会产生一个异常。比如：`bean`抛出一个异常表示缺失或无效的属性。为了避免这种潜在错误的延迟可见性，`ApplicationContext`默认配置`bean`预实例化和`singleton`作用域的原因。这样的方式虽然使得在容器启动时会花费一些时间和内存来创建和保存`bean`，但是对于容器启动时就拥有潜在错误的可见性，这种牺牲在大多数时候是值得的。必要时，我们可以通过指定延迟初始化或`prototype`作用域来覆盖默认值。

如果不存在循环依赖，当一个或多个协作`bean`被作为依赖注入到`bean`时，它们会在注入之前完全配置。这意味着如果`bean A`依赖于`bean B`，`Spring IoC`容易会在完全配置好`bean B`后才执行`bean A`的`setter`方法去注入`bean B`（这其实是通过递归实现，[AbstractAutowireCapableBeanFactory#populateBean]这个方法负责对生成的半成品`beanInstance`进行属性填充，它会根据`BD`中定义的`ResolvedAutowireMode`来决定调用`autowireByName`或者`autowireByType`，在默认的`autowireByName`中对每个属性名都调用了[AbstractBeanFactory#getBean]这个方法来获取相应`bean`，而这个方法的基本逻辑就是从缓存中获取或者创建bean实例，并调用[AbstractAutowireCapableBeanFactory#populateBean]对其进行属性填充）

### 依赖注入和配置项的细节

Spring依赖注入的配置项支持多种形式：

1. 直接量（原始类型、字符串等）
2. `idref`元素：一种将容器中另一个`bean`的`id`（字符串值——而不是引用）传递给`<constructor-arg/>`或`<property/>`元素的防错方式。使用`idref`元素比起直接使用`value`属性更加安全，因为容器在运行时会验证`idref`声明的值是否在`beanName`集合中
3. 对其他`bean`（或者依赖）的引用：使用`ref`属性
4. 内部`bean`：`<property/>`或`<constructor-arg/>`元素内的`<bean/>`元素定义了一个内部`bean`
5. 集合：`<list/>`、`<set/>`、`<map/>`和`<props/>`元素分别设置`Java`集合类型`List`、`Set`、`Map`和`Properties`的属性和参数。`map`的键值和`set`的值可以是：`bean | ref | idref | list | set | map | props | value | null`中的任意一种
6. `Null`和空字符串值
7. 使用`p-namespace`的简写方式：`p-namespace`可以简写`<property/>`元素到`<bean/>`元素的属性上
8. 使用`c-namespace`的简写方式：`c-namespace`可以简写`<constructor-arg/>`元素到`<bean/>`元素的属性上
9. 复合属性名：为属性的属性进行依赖注入，但是要确保属性不为`NULL`,否则会抛出`NPE`

详见[DependencyInjection.java](DependencyInjection.java)的*依赖注入各种不同值的示例*。

### `depends-on`的使用

如果一个`bean`是另一个`bean`的一个依赖，那么这个`bean`十有八九是另一个`bean`的某个依赖注入项，一般我们可以在`XML`配置元数据中通过`<ref/>`元素注入。然而，有时`bean`之间的依赖没有这么直接，比如：数据库驱动程序注册这种需要触发类的静态程序的情况（在使用封装了数据库驱动操作的bean前必须确保写有`Class.forName("com.mysql.cj.jdbc.Driver")的bean先被创建并执行了该指令`）。这就是`depends-on`属性的应用场景，它可以保证在使用此元素的`bean`被初始化之前，显式地强制初始化一个或多个`bean`。（实际逻辑实现于[AbstractBeanFactory#doGetBean]方法，该方法会在缓存中查不到该`bean`，需要创建实例时，先检查其`dependsOn`的`bean`，并调用`getBean`获取依赖`bean`。）

### 延迟初始化`bean`

一个`bean`被声明为延迟初始化，意味着`IoC`容器会延后到它第一次被请求时才创建`bean`实例，而不是在启动时。在`XML`配置元数据中，只需要指定`<bean/>`元素`lazy-init="true"`即可。也可以通过给`<beans/>`元素添加`default-lazy-init="true"`来将`bean`的默认行为改为延迟初始化。

> 如果一个预实例化的单例`bean A`依赖于延迟初始化的单例`bean B`时，`bean B`仍然会在容器启动时就进行实例化。因为容器启动时必须会初始化`bean A`，为了完成`bean A`的初始化，它会去调用`getBean`方法去获取其依赖项，也就意味着在容器初始化时就发生了第一次请求`bean B`实例，那么`bean B`实例理应在容器初始化时就被创建了。详见[AbstractBeanFactory#doGetBean]方法

### 自动注入依赖

`Spring`容器可以自动装配协作`bean`之间的关系。`Spring`可以通过检查`ApplicationContext`的内容，自动为`bean`解析依赖（其他`bean`）。自动装配具有以下优点：

1. 自动装配可以显着减少指定属性或构造函数参数的需要。
2. 自动装配可以随着对象的发展更新配置。例如，如果您需要向类添加依赖项，则无需修改配置即可自动满足该依赖项。因此，自动装配在开发过程中特别有用，当代码库变得更稳定时，可以选择切换回显式装配。

使用基于`XML`的配置元数据时，可以使用`<bean/>`元素的`autowire`属性为`bean`指定自动装配模式。自动装配功能有四种模式，下表描述了四种自动装配模式：

模式|解释
---|---
no|（默认）没有自动装配。`Bean`引用必须由`ref`元素定义。对于较大的项目，不建议更改默认设置，因为明确指定协作者可以提供更好的控制和清晰度。在某种程度上，它记录了系统的结构。
byName|按属性名称自动装配。`Spring`查找与需要自动装配的属性同名的`bean`。例如，如果一个`bean`定义被设置为按名称自动装配并且它包含一个`master`属性（即它有一个 `setMaster`方法），`Spring`会查找一个名为`master`的`bean`定义并使用它来设置属性。
byType|如果容器中只存在一个该属性对应类型的`bean`，则该属性会被自动装配。如果存在多个，则会抛出异常，这表明您不能为该`bean`使用`byType`自动装配。如果没有匹配的`bean`，则不会发生任何事情（未设置属性）。
constructor|类似于`byType`，但适用于构造函数参数。如果容器中没有和构造函数参数类型相匹配的`bean`，则会抛出异常。

在整个项目统一使用自动装配时，其表现最佳。如果通常不使用自动装配，只使用它来连接一两个`bean`定义，它可能会产生让人困惑的行为。

总的来说，自动配置有如下几个限制和缺点：

1. 明确声明的依赖配置（如：在`XML`配置元数据中通过`<property/>`和`constructor-arg`元素配置的依赖）会覆盖自动注入。而且因为设计本身的限制，无法通过自动注入去注入原始类型、字符串等类型，只能自动注入其他`Spring`管理的对象（`bean`和容器内建依赖）的引用
2. 自动注入没有显示的注入声明明确。虽然`Spring`在小心地避免在可能产生意外结果的歧义情况下进行猜测。但是自动注入的情况下`Spring`管理的对象（`bean`和容器内建依赖）之间的关系还是不够文档化
3. 很多为`Spring`容器生成文档的工具可能无法工作在自动注入模式下
4. 如果一个非集合的属性通过自动注入查找到多个满足条件的`bean`定义，那么会抛出异常。 

对于最后一点，有一下几种解决方案：

1. 不再使用自动注入，进行明确的依赖注入的声明
2. 对某些不该被自动注入的`bean`通过`autowire-candidate=“false”`来排除。这样`byType`和`constructor`的自动注入模式（包括`@Autowired`注解）不会将该`bean`作为依赖的候选者
3. 通过将其`<bean/>`元素的`primary`属性设置为`true`，将某个`bean`定义指定为主要候选者
4. 使用基于注解的配置实现更细粒度的控制

### 方法注入

> 方法注入实际上是基于原来的类生成一个代理类，该代理类继承自原来的类，并负责实现被要求注入的方法。

#### 依赖查找方法注入

当两个`singleton`作用域的`bean`或者两个`prototype`作用域的`bean`相互依赖时，`Spring`容器可以很好地保证他们的作用域像定义的那样。对于`prototype`作用域的`bean`依赖`singleton`作用域的`bean`，容器也可以很好地保证。但是，当一个`singleton`作用域的`bean A`依赖于一个`prototype`作用域的`bean B`时，如果我们按照常规写法，那么`bean B`对于`bean A`来说作用域就像是`singleton`了。这是因为当第一次调用`getBean("bean A")`创建`bean A`时，调用了一次`getBean("bean B")`来获取一个新的`bean B`实例，然后将该实例注入到了`bean A`的某个属性中，因为`bean A`是`singleton`作用域的，创建完成的`bean A`会被加入到缓存中，当第二次调用`getBean("bean A")`会从缓存中查找到之间创建的`bean A`实例，同样意味着查找到了其依赖的`bean B`实例。如果`bean B`是一个状态`bean`，很明显我们无法每次都使用同一个`bean B`。为此，我们可能要在`bean A`的某些方法中显示的调用`getBean("bean B")`方法，为此需要实现`ApplicationContextAware`接口，来让`Spring`容器为我们注入应用上下文。如下所示：

```java
public class CommandManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Object process(Map commandState) {
        Command command = createCommand(); // 通过显示的一次依赖查找方法调用来获取一个`command`对象
        command.setState(commandState);
        return command.execute();
    }

    protected Command createCommand() {
        // 依赖于`Spring`的依赖查找接口，因为Spring对于`bean`的依赖只会在创建时进行依赖查找。
        // 因为`CommandManager`是`singleton`作用域的，`Spring`容器在第一次进行完`command`的依赖查找后，就不再进行了
        // 为了保证`command`的`prototype`作用域，这里必须要手动的调用依赖查找方法
        return this.applicationContext.getBean("command", Command.class);
    }
    
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
```

虽然我们通过这种方法实现了功能。但是代码严重依赖于`Spring Framework`了。为了摆脱这种代码坏味道，`Spring`增加了*依赖查找方法注入*功能：

```java
public abstract class CommandManager {

    public Object process(Map commandState) {
        Command command = createCommand(); // 通过显示的一次依赖查找方法调用来获取一个`command`对象
        command.setState(commandState);
        return command.execute();
    }

    // 依赖于`Spring`的依赖查找接口，因为Spring对于`bean`的依赖只会在创建时进行依赖查找。
    // 因为`CommandManager`是`singleton`作用域的，`Spring`容器在第一次进行完`command`的依赖查找后，就不再进行了
    // 为了保证`command`的`prototype`作用域，这里必须要手动的调用依赖查找方法
    // 不过我们不需要自己去实现，只要写好声明，`Spring`会使用代理模式通过继承和代码生成会我们实现该功能
    protected abstract Command createCommand();
}
```

```xml
<beans>
    <!-- a stateful bean deployed as a prototype (non-singleton) -->
    <bean id="myCommand" class="fiona.apple.AsyncCommand" scope="prototype">
        <!-- inject dependencies here as required -->
    </bean>

    <!-- commandProcessor uses statefulCommandHelper -->
    <bean id="commandManager" class="fiona.apple.CommandManager">
        <lookup-method name="createCommand" bean="myCommand"/>
    </bean>
</beans>
```

也可以如下所示直接使用注解：

```java
public abstract class CommandManager {

    public Object process(Object commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    @Lookup("myCommand") // 可以不指明value值，此时`Spring`容器会查找对应类型的bean
    protected abstract Command createCommand();
}
```

不过，当使用注解时，`Spring`推荐使用具体类而不是抽象类，以便它们与`Spring`的组件扫描规则兼容。默认情况下抽象类将被组件扫描忽略，此限制不适用于显式注册或显式导入的`bean`类。

```java
public class CommandManager {

    public Object process(Object commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    // Spring会在生成的子类中`Override`该方法
    @Lookup("myCommand")
    protected Command createCommand() {}
}
```

#### 任意方法替换

详见官方文档，这种功能的使用情况很少

## Bean的作用域

> Bean的作用域实际上指的是调用容器的`getBean`方法时在什么情况下会进行缓存（和该缓存存在于何处）的问题。

`Spring`支持6种作用域，其中4种只有在`web-aware`的`ApplicationContext`（即`WebApplicationContext`的子类）中才可以使用。

作用域|描述
---|---
singleton|默认作用域。`Spring`会在第一次调用`getBean`方法时，将该`bean`加入到[DefaultSingletonBeanRegistry#singletonObjects]缓存中（实际上在创建的过程中可能处于三级缓存的任何一级，但最终都会到达`singletonObjects`这一级缓存中），下次调用`getBean`时，将会返回缓存中的对象实例。这也意味着对于每个容器来说，多次调用`getBean`方法只会获取到同一个对象。
prototype|不会加入三级缓存，每次调用`getBean`方法时都会创建并返回一个新的对象。这也就意味着同一个容器多次调用`getBean`方法去获取同一个`bean`时，得到的都是不同的对象。
request|将单个`bean`定义范围限定为单个`HTTP request`的生命周期。也就是说，在同一个容器的同一个`request`中多次调用`getBean`方法去获取同一个`bean`时，得到的都是同一个对象，而在不同的`request`中获取的是不同的对象。该作用域仅在`web-aware`的`ApplicationContext`中有效。
session|将单个`bean`定义范围限定为单个`HTTP Session`的生命周期。也就是说，在同一个容器的同一个`session`中多次调用`getBean`方法去获取同一个`bean`时，得到的都是同一个对象，而在不同的`session`中获取的是不同的对象。该作用域仅在`web-aware`的`ApplicationContext`中有效。
application|将单个`bean`定义范围限定为单个`HTTP Application`请求的生命周期。也就是说，在同一个容器的同一个`application`中多次调用`getBean`方法去获取同一个`bean`时，得到的都是同一个对象，而在不同的`application`中获取的是不同的对象。该作用域仅在`web-aware`的`ApplicationContext`中有效。
websocket|将单个`bean`定义范围限定为单个`HTTP Websocket`请求的生命周期。也就是说，在同一个容器的同一个`websocket`中多次调用`getBean`方法去获取同一个`bean`时，得到的都是同一个对象，而在不同的`websocket`中获取的是不同的对象。该作用域仅在`web-aware`的`ApplicationContext`中有效。

### singleton和prototype

与其他作用域相比，`Spring`不管理`protopyte`作用域的`bean`的完整生命周期。容器只负责实例化、配置和组装`prototype`对象并将其交给客户端，不会对该实例进一步记录和处理。因此，尽管在所有对象上（不管范围如何）调用初始化生命周期回调方法，但在`prototype`的情况下，不会调用配置的销毁生命周期回调。客户端代码必须清理`prototype`作用域的对象并释放其持有的昂贵资源。要让`Spring`容器释放`prototype`作用域的`bean`持有的资源，请尝试使用自定义`BeanPostProcessor`的实现类，使用其保存对需要清理的`bean`的引用。

`Spring`的单例`bean`概念不同于四人帮 (GoF) 设计模式书中定义的单例模式。 GoF实现单例是通过对对象的范围进行硬编码，以便每个 `ClassLoader`只创建一个特定类的一个实例。而`Spring`是通过容器中的缓存来实现的，其单例只是确保同一个容器对于同一个单例`bean`只会有一个对象实例。

### request、session、application、websocket

`request`、`session`、`application`、`websocket`作用域仅在使用`web-aware`的`ApplicationContext`实现（例如 `XmlWebApplicationContext`）时才可用。如果将这些作用域与常规`Spring IoC`容器（例如` ClassPathXmlApplicationContext`）一起使用，则会抛出`IllegalStateException`异常。

要想使用这些作用域，还要进行初始化的步骤（之所以要进行初始化，是为了让`Spring`容器得知每个线程处理的`request`，容器得知了该线程现在处理的`request`，也就得知了其对应的`session`、`application`等，`Spring`会把对应作用域的`bean`缓存到里面，在被调用`getBean`方法时，通过查找容器中对应的映射即可找到该线程现在服务的`request`，从而得到对应的缓存对象），对于`Servlet`，它表现为在`web.xml`通过配置`RequestContextListener`监听器或配置`RequestContextFilter`过滤器或直接使用`DispatcherServlet`处理请求（`Servlet 3.0`及以后可以通过编程实现相关配置，详见[WebApplicationInitializer]）。

```xml
<web-app>
    <!-- 在web.xml中配置监听器，来确保可以使用额外的四个作用域 -->
    <listener>
        <listener-class>
            org.springframework.web.context.request.RequestContextListener
        </listener-class>
    </listener>
</web-app>
```

```xml
<web-app>
    <!-- 在web.xml中配置所有路径的统一过滤器，来确保可以使用额外的四个作用域 -->
    <filter>
        <filter-name>requestContextFilter</filter-name>
        <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>requestContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

```xml
<web-app>
    <!-- 在web.xml中配置所有路径的统一过滤器，来确保可以使用额外的四个作用域 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
```

`Spring`还提供了额外的简写注解：`@RequestScope`=`@Scope("request")`，`@SessionScope`=`@Scope("session")`，`@ApplicationScope`=`@Scope("application")`

### 作为寿命更长的作用域的`bean`依赖

我们前面已经提到可以依靠*依赖查找方法注入*来将寿命最短的作用域`prototype`注入到`singleton`这种最长寿的作用域`bean`，除此之外，我们还可以使用`AOP`的`scoped-proxy`代理或通过定义`ObjectFactory`、`ObjectProvider`等对象工厂类。

通过使用`AOP`的`scoped-proxy`代理，我们每次调用被作用域代理的`bean`对象的相关方法时，它会做一个相当于实际调用一次`getBean("targetBeanName.testB2")`方法的操作(详见[ScopedProxyUtils#createScopedProxy]可以发现`targetBeanName`是原来的`bean`name加`targetBeanName.`前缀。而原`bean`名对应于一个代理对象)，然后用获取对象去执行实际执行的方法。详见[BeanScope.java](BeanScope.java)

默认情况下，当`Spring`容器为使用`<aop:scoped-proxy/>`元素标记的 bean 创建代理时，将基于`CGLIB`来实现。`CGLIB`代理只拦截公共方法调用。不要在这样的代理上调用非公共方法。它们不会委托给实际作用域的目标对象。或者，可以配置`Spring`容器，将`<aop:scoped-proxy/>`元素的`proxy-target-class`属性的值指定`false`来基于`JDK`接口的代理。使用基于`JDK`接口来实现代理。然而，这也意味着被代理的作用域`bean`的类必须至少实现一个接口，并且所有的协作者必须通过其接口之一来引用该`bean`。以下示例显示了基于接口的代理：

```xml
<beans>
    <!-- DefaultUserPreferences implements the UserPreferences interface -->
    <bean id="userPreferences" class="com.stuff.DefaultUserPreferences" scope="session">
        <aop:scoped-proxy proxy-target-class="false"/>
    </bean>

    <bean id="userManager" class="com.stuff.UserManager">
        <property name="userPreferences" ref="userPreferences"/>
    </bean>
</beans>
```

> 如果`aop:scoped-proxy`添加在`FactoryBean`的`bean`定义上，那么生成的代理的目标对象是`FactoryBean`实例，而不是其`getObject`方法创建的`bean`实例

### 自定义作用域

`bean`作用域机制是可扩展的。`Spring`允许自定义作用域，甚至重新定义现有作用域（这被认为是一种不好的做法）。另外，我们无法重定义内置的`singleton`和`prototype`作用域（这是因为在[AbstractBeanFactory#registerScope]中写明了，如果注册的Scope的name是`singleton`或`prototype`就抛出`IllegalArgumentException`异常）。

自定义作用域实际上就是创建一个`Scope`接口的实现，然后将其注册到容器中。自定义作用域可以参考`org.springframework.context.support.SimpleThreadScope`，这是`Spring`自带的一个自定义的线程作用域。

详见[CustomBeanScope.java](CustomBeanScope.java)

## 自定义Bean的性质

Spring Framework 提供了许多可用于自定义 bean 性质的接口。主要有如下3类：

1. 生命周期回调
2. 应用上下文感知（`org.springframework.context.ApplicationContextAware`）和`BeanName`感知（`org.springframework.beans.factory.BeanNameAware`）
3. 其他感知接口（即其他`org.springframework.context.Aware`接口的子接口）

### 生命周期回调

#### `bean`的初始化和销毁生命周期回调

在初始化bean时(详见[AbstractAutowireCapableBeanFactory#initializeBean])使用了如下的调用顺序：

1. 先调用了`bean`上定义的通用感知方法（`Aware`接口），将容器内建依赖注入到了`bean`中。（即调用了`invokeAwareMethods`方法，调用`BeanNameAware`、`BeanClassLoaderAware`、`BeanFactoryAware`接口中定义的方法）
2. 然后调用了所有`BeanPostProcessor`的`postProcessBeforeInitialization`方法（即调用了`applyBeanPostProcessorsBeforeInitialization`方法）。而`@PostConstruct`注解的解析就是通过调用[InitDestroyAnnotationBeanPostProcessor#postProcessBeforeInitialization]实现的（在该方法中调用`invokeInitMethods`方法通过反射调用了所有配置的自定义初始化方法）。
3. 然后调用了`bean`上相关的初始化方法。（即调用了`invokeInitMethods`方法）先执行[InitializingBean#afterPropertiesSet]方法，然后执行`init-method`属性定义的自定义方法（如果该方法名也叫做`afterPropertiesSet`，且该`bean`实现了`InitializingBean`，那么该方法已经执行过了，那就不会再执行了）
4. 最后调用了所有`BeanPostProcessor`的`postProcessAfterInitialization`方法（即调用了`applyBeanPostProcessorsAfterInitialization`方法）。

所以，对应的调用顺序为：`@PostConstruct` > `InitializingBean#afterPropertiesSet` > `init-method`

另外，[DisposableBeanAdapter#destroy]方法的处理逻辑如下：

1. 调用所有的[DestructionAwareBeanPostProcessor#postProcessBeforeDestruction]方法，其中定义了`@PreDestroy`注解的处理
2. 调用[DisposableBean#destroy]方法
3. 调用自定义的销毁方法（没有指定`destroy-method`或`default-destroy-method`属性时，会在构建`DisposableBeanAdapter`类时进行推断，查找`AutoCloseable#close`方法或者名叫做`close`、`shutdown`的`public`无参方法）

所有销毁生命周期的调用顺序为：`@PreDestroy` > `DisposableBean#destroy` > `destroy-method`

详见[BeanLifeCycleCallback.java](BeanLifeCycleCallback.java)

#### 容器的启动和关闭等生命周期回调

任何`Spring`管理的对象都可以实现`Lifecycle`接口，从而接受容器的启动、停止等通知。`Spring`容器会将这些调用级联到该上下文中定义的所有`Lifecycle`实现上，这部分逻辑委托给了`LifecycleProcessor`实现类，默认的实现是`DefaultLifecycleProcessor`，该策略依赖于`Lifecycle`的子接口`SmartLifecycle`，它会在容器启动时，自动调用所有注册的`autoStartup`的`SmartLifecycle`的`start`方法，并在容器销毁时（即调用容器的close方法，一般框架都会在最后调用该方法来释放资源），自动调用其`stop(Runnable)`方法（该方法实现中一般会去调用其继承`Lifecycle`得到的`stop`方法，将实际逻辑写到该方法）。

`LifecycleProcessor`接口也是`Lifecycle`的某个子接口，其中额外定义了`onRefresh`和`onClose`两个接口，容器会在其`refresh`方法中调用其中注册的`LifecycleProcessor`实现的`onRefresh`方法（详见[AbstractApplicationContext#refresh]>[AbstractApplicationContext#finishRefresh]），在其`doClose`方法中调用其中注册的`LifecycleProcessor`实现的`onClose`方法（详见[AbstractApplicationContext#doClose]）。

详见[IoCLifeCycleCallback.java](IoCLifeCycleCallback.java)

### `ApplicationContextAware`和`BeanNameAware`

这些接口的调用都在[AbstractAutowireCapableBeanFactory#initializeBean]方法中，该方法在[AbstractAutowireCapableBeanFactory#populateBean]方法之后执行（详见[AbstractAutowireCapableBeanFactory#doCreateBean]，另外`populateBean`方法主要负责对bean的其他依赖进行自动填充），在`initializeBean`中先执行了`Aware`接口方法，然后调用了`@PostConstruct`、`InitializingBean#afterPropertiesSet`、`initMethod`等自定义初始化方法。

### 其他感知接口

除了`ApplicationContextAware`和`BeanNameAware`接口，Spring还提供了很多其他的感知接口，如下表所示：

| 感知接口名                     | 注入的依赖                                                                 |
| ------------------------------ | -------------------------------------------------------------------------- |
| ApplicationContextAware        | 感知该bean所在的Spring容器(ApplicationContext)                             |
| ApplicationEventPublisherAware | 感知Spring事件发布者(ApplicationEventPublisher)                            |
| BeanClassLoaderAware           | 感知该bean所用的类加载器(ClassLoader)                                      |
| BeanFactoryAware               | 感知创建该bean所用的Bean工厂(BeanFactory)                                  |
| BeanNameAware                  | 感知创建该bean的名字(String)                                               |
| LoadTimeWeaverAware            | 感知该AspectJ对应本Bean的LTW(LoadTimeWeaver)                               |
| MessageSourceAware             | 注入MessageSource对象(MessageSource)                                       |
| NotificationPublisherAware     | 注入Spring JMX通知发布者(NotificationPublisher)                            |
| ResourceLoaderAware            | 配置较底层访问资源的加载器(ResourceLoader)                                 |
| ServletConfigAware             | 注入运行容器的`ServletConfig`,仅当`ApplicationContext`是`web-aware`时有效  |
| ServletContextAware            | 注入运行容器的`ServletContext`,仅当`ApplicationContext`是`web-aware`时有效 |

使用这些接口会代码与`Spring API`耦合起来，并且不再遵循控制反转风格。因此，`Spring`官方不建议使用这些感知器

## Bean定义的继承机制

`bean`的定义会包含很多配置信息，包括构造器参数，属性值和与容器行为相关的信息（如：初始化方法，静态工厂方法名等）。一个`bean`定义子类可以继承父类的相关配置信息。子类也可以在必要时覆盖父类的定义。使用继承机制可以节省很多输入（类似于`Java`类的继承）。实际上，这是一种模板的方式。

如果以编程方式使用`ApplicationContext`接口，那么子`bean`定义由`ChildBeanDefinition`类表示。但是，一般在类中以声明方式配置`bean`定义，例如：使用`ClassPathXmlApplicationContext`，然后使用基于`XML`的配置元数据。

如果没有特别指定，子`bean`定义会使用父定义中声明`bean`类（即`class`属性）。不过，子类也可以通过明确指定`class`属性来覆盖父定义。这时，子`bean`定义声明的类必须是与父定义声明的类兼容的类型（即它必须能够接受父定义中声明的全部属性值）。

子`bean`定义从父定义继承作用域、构造器参数值、属性值，并可以覆盖父定义的方法（初始化方法、销毁方法、静态工厂方法等）和添加新值。其余设置始终取自子定义，如：`dependsOn`、`autowire`模式、`autowire-candidate`和`lazy-init`。

详见[BeanDefinitionInheritance.java](BeanDefinitionInheritance.java)

## 容器的扩展点

`Spring IoC`提供了很多接口来扩展容器。

### 使用`BeanPostProcessor`自定义Bean

`BeanPostProcessor`接口定义了回调方法，可以通过实现这些方法来提供自定义的（或覆盖容器默认的）实例化逻辑、依赖解析逻辑等。如果要在`Spring`容器完成对`bean`的实例化、配置和初始化之后（即`populateBean`之后）实现一些自定义逻辑，可以插入一个或多个自定义的`BeanPostProcessor`实现。

可以通过实现`PriorityOrdered`或`Ordered`接口，来定义`BeanPostProcessor`的顺序（详见[PostProcessorRegistrationDelegate#registerBeanPostProcessors]）。
