package com.sjli.spring.IOC.profile;


import com.sjli.spring.IOC.useResource.service.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.time.ZoneId;

/*
使用条件装配
阅读: 191381
开发应用程序时，我们会使用开发环境，例如，使用内存数据库以便快速启动。而运行在生产环境时，
我们会使用生产环境，例如，使用MySQL数据库。如果应用程序可以根据自身的环境做一些适配，无疑会更加灵活。

Spring为应用程序准备了Profile这一概念，用来表示不同的环境。例如，我们分别定义开发、测试和生产这3个环境：

native
test
production

创建某个Bean时，Spring容器可以根据注解@Profile来决定是否创建。例如，以下配置：
 */

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Profile("!test")
    ZoneId createZoneId(){
        return ZoneId.systemDefault();
    }

    @Bean
    @Profile("test")
    ZoneId createZoneIdForTest() {
        return ZoneId.of("America/New_York");
    }
    /*
    如果当前的Profile设置为test，则Spring容器会调用createZoneIdForTest()创建ZoneId，否则，调用createZoneId()创建ZoneId。注意到@Profile("!test")表示非test环境。

    在运行程序时，加上JVM参数-Dspring.profiles.active=test就可以指定以test环境启动。
    实际上，Spring允许指定多个Profile，例如：

    -Dspring.profiles.active=test,master
    可以表示test环境，并使用master分支代码。

    要满足多个Profile条件，可以这样写：

    @Bean
    @Profile({ "test", "master" }) // 同时满足test和master
    ZoneId createZoneId() {
        ...
    }
     */

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppService appService = context.getBean(AppService.class);
        System.out.println(appService.toString());
        String logo  = appService.getLogo();
        System.out.println(logo);
    }
}
