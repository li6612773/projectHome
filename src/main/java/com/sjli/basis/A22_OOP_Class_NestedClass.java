package com.sjli.basis;

import java.util.HashMap;

public class A22_OOP_Class_NestedClass {
    public static void main(String[] args) {
        //Inner Class
        Outer1 outer1 = new Outer1("Nested"); // 实例化一个Outer
        Outer1.Inner1 inner1 = outer1.new Inner1(); // 实例化一个Inner
        inner1.hello();
        //Anonymous Class
        Outer3 outer3 = new Outer3("Nested");
        outer3.asyncHello();

        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>() {}; // 匿名类!
        HashMap<String, String> map3 = new HashMap<>() {
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(map3.get("A"));
        //Static Nested Class
        Outer4.StaticNested sn = new Outer4.StaticNested();
        sn.hello();
    }
}
//内部类
    /*
    在Java程序中，通常情况下，
    我们把不同的类组织在不同的包下面，对于一个包下面的类来说，它们是在同一层次，没有父子关系：

    java.lang
    ├── Math
    ├── Runnable
    ├── String
    └── ...
    还有一种类，它被定义在另一个类的内部，所以称为内部类（Nested Class）。
    Java的内部类分为好几种，通常情况用得不多，但也需要了解它们是如何使用的。
     */
//Inner Class
    //如果一个类定义在另一个类的内部，这个类就是Inner Class：
    class Outer2 {
        class Inner2 {
            // 定义了一个Inner Class
        }
    }
    //上述定义的Outer是一个普通类，而Inner是一个Inner Class，
    // 它与普通类有个最大的不同，就是Inner Class的实例不能单独存在，
    // 必须依附于一个Outer Class的实例。示例代码如下：

    class Outer1 {
        private String name;

        Outer1(String name) {
            this.name = name;
        }

        class Inner1 {
            void hello() {
                System.out.println("Hello, " + Outer1.this.name);
            }
        }
    }
    /*
    public class Main {
        public static void main(String[] args) {
            Outer outer = new Outer("Nested"); // 实例化一个Outer
            Outer.Inner inner = outer.new Inner(); // 实例化一个Inner
            inner.hello();
        }
    }
     */

    //观察上述代码，要实例化一个Inner，我们必须首先创建一个Outer的实例，
    // 然后，调用Outer实例的new来创建Inner实例：
    //Outer.Inner inner = outer.new Inner();

    //这是因为Inner Class除了有一个this指向它自己，还隐含地持有一个Outer Class实例，
    // 可以用Outer.this访问这个实例。所以，实例化一个Inner Class不能脱离Outer实例。
    //
    //Inner Class和普通Class相比，除了能引用Outer实例外，还有一个额外的“特权”，
    // 就是可以修改Outer Class的private字段，因为Inner Class的作用域在Outer Class内部，
    // 所以能访问Outer Class的private字段和方法。
    //
    //观察Java编译器编译后的.class文件可以发现，Outer类被编译为Outer.class，
    // 而Inner类被编译为Outer$Inner.class。

//Anonymous Class
    //还有一种定义Inner Class的方法，它不需要在Outer Class中明确地定义这个Class，
    // 而是在方法内部，通过匿名类（Anonymous Class）来定义。示例代码如下：
    /*
    public class Main {
        public static void main(String[] args) {
            Outer outer3 = new Outer3("Nested");
            outer3.asyncHello();
        }
    }
     */
    class Outer3{
        private String name;

        Outer3(String name) {
            this.name = name;
        }

        void asyncHello() {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello, " + Outer3.this.name);
                }
            };
            new Thread(r).start();
        }
    }
    //观察asyncHello()方法，我们在方法内部实例化了一个Runnable。Runnable本身是接口，
    // 接口是不能实例化的，所以这里实际上是定义了一个实现了Runnable接口的匿名类，
    // 且通过new实例化该匿名类，然后转型为Runnable。
    // 在定义匿名类的时候就必须实例化它，定义匿名类的写法如下：
    /*
    Runnable r = new Runnable() {
        // 实现必要的抽象方法...
    };
     */
    //匿名类和Inner Class一样，可以访问Outer Class的private字段和方法。
    // 之所以我们要定义匿名类，是因为在这里我们通常不关心类名，比直接定义Inner Class可以少写很多代码。
    //
    //观察Java编译器编译后的.class文件可以发现，Outer类被编译为Outer.class，
    // 而匿名类被编译为Outer$1.class。如果有多个匿名类，
    // Java编译器会将每个匿名类依次命名为Outer$1、Outer$2、Outer$3……

    //除了接口外，匿名类也完全可以继承自普通类。观察以下代码：
    /*
    public class Main {
        public static void main(String[] args) {
            HashMap<String, String> map1 = new HashMap<>();
            HashMap<String, String> map2 = new HashMap<>() {}; // 匿名类!
            HashMap<String, String> map3 = new HashMap<>() {
                {
                    put("A", "1");
                    put("B", "2");
                }
            };
            System.out.println(map3.get("A"));
        }
    }
     */
    //map1是一个普通的HashMap实例，但map2是一个匿名类实例，只是该匿名类继承自HashMap。
    // map3也是一个继承自HashMap的匿名类实例，并且添加了static代码块来初始化数据。
    // 观察编译输出可发现Main$1.class和Main$2.class两个匿名类文件。

//Static Nested Class
    //最后一种内部类和Inner Class类似，但是使用static修饰，称为静态内部类（Static Nested Class）：
    /*
    public class Main {
        public static void main(String[] args) {
            Outer.StaticNested sn = new Outer.StaticNested();
            sn.hello();
        }
    }
    */

    class Outer4 {
        private static String NAME = "OUTER";

        private String name;

        Outer4(String name) {
            this.name = name;
        }

        static class StaticNested {
            void hello() {
                System.out.println("Hello, " + Outer4.NAME);
            }
        }
    }
    //用static修饰的内部类和Inner Class有很大的不同，它不再依附于Outer的实例，
    // 而是一个完全独立的类，因此无法引用Outer.this，但它可以访问Outer的private静态字段和静态方法。
    // 如果把StaticNested移到Outer之外，就失去了访问private的权限。




