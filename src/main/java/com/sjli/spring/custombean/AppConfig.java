package com.sjli.spring.custombean;

import com.sjli.spring.custombean.service.User;
import com.sjli.spring.custombean.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
Scope
对于Spring容器来说，当我们把一个Bean标记为@Component后，它就会自动为我们创建一个单例（Singleton），
即容器初始化时创建Bean，容器关闭前销毁Bean。在容器运行期间，
我们调用getBean(Class)获取到的Bean总是同一个实例。

还有一种Bean，我们每次调用getBean(Class)，容器都返回一个新的实例，
这种Bean称为Prototype（原型），它的生命周期显然和Singleton不同。声明一个Prototype的Bean时，
需要添加一个额外的@Scope注解：

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // @Scope("prototype")
public class MailSession {
    ...
}
 */

@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}

/*
使用的实现类是AnnotationConfigApplicationContext，必须传入一个标注了@Configuration的类名。

此外，AppConfig还标注了@ComponentScan，它告诉容器，自动搜索当前类所在的包以及子包，
把所有标注为@Component的Bean自动创建出来，并根据@Autowired进行装配。

整个工程结构如下：

spring-ioc-annoconfig
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── itranswarp
                    └── learnjava
                        ├── AppConfig.java
                        └── service
                            ├── MailService.java
                            ├── User.java
                            └── UserService.java
使用Annotation配合自动扫描能大幅简化Spring的配置，我们只需要保证：

每个Bean被标注为@Component并正确使用@Autowired注入；
配置类被标注为@Configuration和@ComponentScan；
所有Bean均在指定包以及子包内。
使用@ComponentScan非常方便，但是，我们也要特别注意包的层次结构。通常来说，启动配置AppConfig位于自定义的顶层包
（例如com.itranswarp.learnjava），其他Bean按类别放入子包。
 */
