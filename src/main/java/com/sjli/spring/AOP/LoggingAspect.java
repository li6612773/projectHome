package com.sjli.spring.AOP;

/*

装配AOP
阅读: 470812
在AOP编程中，我们经常会遇到下面的概念：

Aspect：切面，即一个横跨多个核心逻辑的功能，或者称之为系统关注点；
Joinpoint：连接点，即定义在应用程序流程的何处插入切面的执行；
Pointcut：切入点，即一组连接点的集合；
Advice：增强，指特定连接点上执行的动作；
Introduction：引介，指为一个已有的Java对象动态地增加新的接口；
Weaving：织入，指将切面整合到程序的执行流程中；
Interceptor：拦截器，是一种实现增强的方式；
Target Object：目标对象，即真正执行业务的核心逻辑对象；
AOP Proxy：AOP代理，是客户端持有的增强后的对象引用。
看完上述术语，是不是感觉对AOP有了进一步的困惑？其实，我们不用关心AOP创造的“术语”，只需要理解AOP本质上只是一种代理模式的实现方式，在Spring的容器中实现AOP特别方便。

我们以UserService和MailService为例，这两个属于核心业务逻辑，现在，我们准备给UserService的每个业务方法执行前添加日志，给MailService的每个业务方法执行前后添加日志，在Spring中，需要以下步骤：

首先，我们通过Maven引入Spring对AOP的支持：

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>${spring.version}</version>
</dependency>
上述依赖会自动引入AspectJ，使用AspectJ实现AOP比较方便，因为它的定义比较简单。

然后，我们定义一个LoggingAspect：
 */

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    //在执行UserService的每个方法前执行:
    @Before("execution(public * com.itranswarp.learnjava.service.UserService.*(..))")

    public void doAccessCheck(){
        System.err.println("[Before] do access check...");
    }

    // 在执行MailService的每个方法前后执行:
    @Around("execution(public * com.itranswarp.learnjava.service.MailService.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable{
        System.err.println("[Around] start " + pjp.getSignature());
        Object retVal = pjp.proceed();
        System.err.println("[Around] done " + pjp.getSignature());
        return retVal;
    }
}
