package com.sjli.spring.IOC.annotation;

import com.sjli.spring.IOC.annotation.service.User;
import com.sjli.spring.IOC.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//最后，编写一个AppConfig类启动容器：
//除了main()方法外，AppConfig标注了@Configuration，表示它是一个配置类，因为我们创建ApplicationContext时：

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


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
