package com.sjli.spring.IOC.A5_injectProperties;


import com.sjli.spring.IOC.A5_injectProperties.service.AppService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.time.ZoneId;

/**
 * 在开发应用程序时，经常需要读取配置文件。最常用的配置方法是以key=value的形式写在.properties文件中。
 *
 * 例如，MailService根据配置的app.zone=Asia/Shanghai来决定使用哪个时区。要读取配置文件，我们可以使用上一节讲到的Resource来读取位于classpath下的一个app.properties文件。但是，这样仍然比较繁琐。
 *
 * Spring容器还提供了一个更简单的@PropertySource来自动读取配置文件。我们只需要在@Configuration配置类上再添加一个注解：
 */
//可见，先使用@PropertySource读取配置文件，然后通过@Value以${key:defaultValue}的形式注入，可以极大地简化读取配置的麻烦。
@Configuration
@ComponentScan
@PropertySource("app.properties") // 表示读取classpath的app.properties
public class AppConfig {
    //"${app.zone}"表示读取key为app.zone的value，如果key不存在，启动将报错；
    //"${app.zone:Z}"表示读取key为app.zone的value，但如果key不存在，就使用默认值Z。
    @Value("${app.zone:Z}")
    String zoneId;

    //可以把注入的注解写到方法参数中：
    @Bean
    ZoneId createZoneId(@Value("${app.zone:Z}") String zoneId){
        return ZoneId.of(zoneId);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppService appService = context.getBean(AppService.class);
        System.out.println(appService.toString());
        String logo  = appService.getLogo();
        System.out.println(logo);
    }
}
