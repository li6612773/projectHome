package com.sjli;


import com.sun.tools.javac.Main;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class D02_Log_CommonsLogging {

    static final Log log = LogFactory.getLog(D02_Log_CommonsLogging.class);

    public static void main(String[] args) {
    //使用Commons Logging
        //和Java标准库提供的日志不同，Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
        //
        //Commons Logging的特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。默认情况下，Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），如果没有找到Log4j，再使用JDK Logging。
        //
        //使用Commons Logging只需要和两个类打交道，并且只有两步：
        //
        //第一步，通过LogFactory获取Log类的实例； 第二步，使用Log实例的方法打日志。
        //
        //示例代码如下：

        log.info("start...");
        log.warn("end.");
        //运行上述代码，肯定会得到编译错误，类似error:
        // package org.apache.commons.logging does not exist（找不到org.apache.commons.logging这个包）。
        // 因为Commons Logging是一个第三方提供的库，所以，必须先把它下载下来。下载后，
        // 解压，找到commons-logging-1.2.jar这个文件，再把Java源码Main.java放到一个目录下，例如work目录：
        //
        //work
        // │
        // ├─ commons-logging-1.2.jar
        // │
        // └─ Main.java
        //然后用javac编译Main.java，编译的时候要指定classpath，不然编译器找不到我们引用的
        // org.apache.commons.logging包。编译命令如下：
        /*
        javac -cp commons-logging-1.2.jar Main.java
        如果编译成功，那么当前目录下就会多出一个Main.class文件：

        work
        │
        ├─ commons-logging-1.2.jar
        │
        ├─ Main.java
        │
        └─ Main.class
        现在可以执行这个Main.class，使用java命令，也必须指定classpath，命令如下：

        java -cp .;commons-logging-1.2.jar Main
        注意到传入的classpath有两部分：一个是.，一个是commons-logging-1.2.jar，用;分割。.表示当前目录，如果没有这个.，JVM不会在当前目录搜索Main.class，就会报错。

        如果在Linux或macOS下运行，注意classpath的分隔符不是;，而是:：

        java -cp .:commons-logging-1.2.jar Main
        运行结果如下：

        Mar 02, 2019 7:15:31 PM Main main
        INFO: start...
        Mar 02, 2019 7:15:31 PM Main main
        WARNING: end.
        Commons Logging定义了6个日志级别：

        FATAL
        ERROR
        WARNING
        INFO
        DEBUG
        TRACE
        默认级别是INFO。

        使用Commons Logging时，如果在静态方法中引用Log，通常直接定义一个静态类型变量：

        // 在静态方法中引用Log:
        public class Main {
            static final Log log = LogFactory.getLog(Main.class);

            static void foo() {
                log.info("foo");
            }
        }
        在实例方法中引用Log，通常定义一个实例变量：

        // 在实例方法中引用Log:
        public class Person {
            protected final Log log = LogFactory.getLog(getClass());

            void foo() {
                log.info("foo");
            }
        }
        注意到实例变量log的获取方式是LogFactory.getLog(getClass())，虽然也可以用LogFactory.getLog(Person.class)，但是前一种方式有个非常大的好处，就是子类可以直接使用该log实例。例如：

        // 在子类中使用父类实例化的log:
        public class Student extends Person {
            void bar() {
                log.info("bar");
            }
        }
        由于Java类的动态特性，子类获取的log字段实际上相当于LogFactory.getLog(Student.class)，但却是从父类继承而来，并且无需改动代码。

        此外，Commons Logging的日志方法，例如info()，除了标准的info(String)外，还提供了一个非常有用的重载方法：info(String, Throwable)，这使得记录异常更加简单：

        try {
            ...
        } catch (Exception e) {
            log.error("got exception!", e);
        }
         */
    }
}
