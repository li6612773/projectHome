package com.sjli.spring.IOC.A4_useResource;


import com.sjli.spring.IOC.A4_useResource.service.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(com.sjli.spring.IOC.A4_useResource.AppConfig.class);
        AppService appService = context.getBean(AppService.class);
        System.out.println(appService.toString());
        String logo  = appService.getLogo();
        System.out.println(logo);
    }
}
