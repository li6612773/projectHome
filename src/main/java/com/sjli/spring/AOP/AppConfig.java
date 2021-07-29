package com.sjli.spring.AOP;


import com.sjli.spring.AOP.service.UserService;
import com.sjli.spring.AOP.service.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.time.ZoneId;

/*
紧接着，我们需要给@Configuration类加上一个@EnableAspectJAutoProxy注解：

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {
    ...
}
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    @Primary //指定为主要Bean
    @Qualifier("z")
    ZoneId createZoneId(){
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8(){
        return ZoneId.of("UTC+08:00");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        com.sjli.spring.AOP.service.UserService userService = context.getBean(UserService.class);

        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}

/*
Spring的IoC容器看到这个注解，就会自动查找带有@Aspect的Bean，然后根据每个方法的@Before、@Around等注解把AOP注入到特定的Bean中。执行代码，我们可以看到以下输出：

[Before] do access check...
[Around] start void com.itranswarp.learnjava.service.MailService.sendRegistrationMail(User)
Welcome, test!
[Around] done void com.itranswarp.learnjava.service.MailService.sendRegistrationMail(User)
[Before] do access check...
[Around] start void com.itranswarp.learnjava.service.MailService.sendLoginMail(User)
Hi, Bob! You are logged in at 2020-02-14T23:13:52.167996+08:00[Asia/Shanghai]
[Around] done void com.itranswarp.learnjava.service.MailService.sendLoginMail(User)
这说明执行业务逻辑前后，确实执行了我们定义的Aspect（即LoggingAspect的方法）。
 */

/*
其实AOP的原理非常简单。我们以LoggingAspect.doAccessCheck()为例，要把它注入到UserService的每个public方法中，最简单的方法是编写一个子类，并持有原始实例的引用：

public UserServiceAopProxy extends UserService {
    private UserService target;
    private LoggingAspect aspect;

    public UserServiceAopProxy(UserService target, LoggingAspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    public User login(String email, String password) {
        // 先执行Aspect的代码:
        aspect.doAccessCheck();
        // 再执行UserService的逻辑:
        return target.login(email, password);
    }

    public User register(String email, String password, String name) {
        aspect.doAccessCheck();
        return target.register(email, password, name);
    }

    ...
}
    这些都是Spring容器启动时为我们自动创建的注入了Aspect的子类，它取代了原始的UserService
    （原始的UserService实例作为内部变量隐藏在UserServiceAopProxy中）。
    如果我们打印从Spring容器获取的UserService实例类型，
    它类似UserService$$EnhancerBySpringCGLIB$$1f44e01c，实际上是Spring使用CGLIB动态创建的子类，
    但对于调用方来说，感觉不到任何区别。

     Spring对接口类型使用JDK动态代理，对普通类使用CGLIB创建子类。
     如果一个Bean的class是final，Spring将无法为其创建子类。

     可见，虽然Spring容器内部实现AOP的逻辑比较复杂（需要使用AspectJ解析注解，并通过CGLIB实现代理类），
     但我们使用AOP非常简单，一共需要三步：

    定义执行方法，并在方法上通过AspectJ的注解告诉Spring应该在何处调用此方法；
    标记@Component和@Aspect；
    在@Configuration类上标注@EnableAspectJAutoProxy。
    至于AspectJ的注入语法则比较复杂，请参考Spring文档。

    Spring也提供其他方法来装配AOP，但都没有使用AspectJ注解的方式来得简洁明了，所以我们不再作介绍。

拦截器类型
    顾名思义，拦截器有以下类型：

    @Before：这种拦截器先执行拦截代码，再执行目标代码。如果拦截器抛异常，那么目标代码就不执行了；

    @After：这种拦截器先执行目标代码，再执行拦截器代码。无论目标代码是否抛异常，拦截器代码都会执行；

    @AfterReturning：和@After不同的是，只有当目标代码正常返回时，才执行拦截器代码；

    @AfterThrowing：和@After不同的是，只有当目标代码抛出了异常时，才执行拦截器代码；

    @Around：能完全控制目标代码是否执行，并可以在执行前后、抛异常后执行任意拦截代码，
    可以说是包含了上面所有功能。


使用注解装配AOP
    阅读: 220017
    上一节我们讲解了使用AspectJ的注解，并配合一个复杂的execution(* xxx.Xyz.*(..))语法来定义应该如何装配AOP。

    在实际项目中，这种写法其实很少使用。假设你写了一个SecurityAspect：

    @Aspect
    @Component
    public class SecurityAspect {
        @Before("execution(public * com.itranswarp.learnjava.service.*.*(..))")
        public void check() {
            if (SecurityContext.getCurrentUser() == null) {
                throw new RuntimeException("check failed");
            }
        }
    }
    基本能实现无差别全覆盖，即某个包下面的所有Bean的所有方法都会被这个check()方法拦截。

    还有的童鞋喜欢用方法名前缀进行拦截：

    @Around("execution(public * update*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        // 对update开头的方法切换数据源:
        String old = setCurrentDataSource("master");
        Object retVal = pjp.proceed();
        restoreCurrentDataSource(old);
        return retVal;
    }
    这种非精准打击误伤面更大，因为从方法前缀区分是否是数据库操作是非常不可取的。

    我们在使用AOP时，要注意到虽然Spring容器可以把指定的方法通过AOP规则装配到指定的Bean的指定方法前后，但是，如果自动装配时，因为不恰当的范围，容易导致意想不到的结果，即很多不需要AOP代理的Bean也被自动代理了，并且，后续新增的Bean，如果不清楚现有的AOP装配规则，容易被强迫装配。

    使用AOP时，被装配的Bean最好自己能清清楚楚地知道自己被安排了。例如，Spring提供的@Transactional就是一个非常好的例子。如果我们自己写的Bean希望在一个数据库事务中被调用，就标注上@Transactional：

    @Component
    public class UserService {
        // 有事务:
        @Transactional
        public User createUser(String name) {
            ...
        }

        // 无事务:
        public boolean isValidName(String name) {
            ...
        }

        // 有事务:
        @Transactional
        public void updateUser(User user) {
            ...
        }
    }
    或者直接在class级别注解，表示“所有public方法都被安排了”：

    @Component
    @Transactional
    public class UserService {
        ...
    }
    通过@Transactional，某个方法是否启用了事务就一清二楚了。因此，装配AOP的时候，使用注解是最好的方式。

    我们以一个实际例子演示如何使用注解实现AOP装配。为了监控应用程序的性能，我们定义一个性能监控的注解：

    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface MetricTime {
        String value();
    }
    在需要被监控的关键方法上标注该注解：

    @Component
    public class UserService {
        // 监控register()方法性能:
        @MetricTime("register")
        public User register(String email, String password, String name) {
            ...
        }
        ...
    }
    然后，我们定义MetricAspect：

    @Aspect
    @Component
    public class MetricAspect {
        @Around("@annotation(metricTime)")
        public Object metric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
            String name = metricTime.value();
            long start = System.currentTimeMillis();
            try {
                return joinPoint.proceed();
            } finally {
                long t = System.currentTimeMillis() - start;
                // 写入日志或发送至JMX:
                System.err.println("[Metrics] " + name + ": " + t + "ms");
            }
        }
    }
    注意metric()方法标注了@Around("@annotation(metricTime)")，它的意思是，符合条件的目标方法是带有@MetricTime注解的方法，因为metric()方法参数类型是MetricTime（注意参数名是metricTime不是MetricTime），我们通过它获取性能监控的名称。

    有了@MetricTime注解，再配合MetricAspect，任何Bean，只要方法标注了@MetricTime注解，就可以自动实现性能监控。运行代码，输出结果如下：

    Welcome, Bob!
    [Metrics] register: 16ms
 */