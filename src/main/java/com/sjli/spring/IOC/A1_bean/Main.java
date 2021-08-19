package com.sjli.spring.IOC.A1_bean;

import com.sjli.spring.IOC.A1_bean.service.User;
import com.sjli.spring.IOC.A1_bean.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2021/8/19 14:29
 * @Created by steven
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        以看到，Spring容器就是ApplicationContext，它是一个接口，有很多实现类，
//        这里我们选择ClassPathXmlApplicationContext，表示它会自动从classpath中查找指定的XML配置文件。
//        获得了ApplicationContext的实例，就获得了IoC容器的引用。
//        从ApplicationContext中我们可以根据Bean的ID获取Bean，但更多的时候我们根据Bean的类型获取Bean的引用：
        UserService userService = context.getBean(UserService.class);
//        Spring还提供另一种IoC容器叫BeanFactory，使用方式和ApplicationContext类似：
//        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application.xml"));
//        MailService mailService = factory.getBean(MailService.class);
//        BeanFactory和ApplicationContext的区别在于，BeanFactory的实现是按需创建，
//        即第一次获取Bean时才创建这个Bean，而ApplicationContext会一次性创建所有的Bean。
//        实际上，ApplicationContext接口是从BeanFactory接口继承而来的，并且，
//        ApplicationContext提供了一些额外的功能，包括国际化支持、事件和通知机制等。
//        常情况下，我们总是使用ApplicationContext，很少会考虑使用BeanFactory。
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}
