package com.sjli.basis;

public class A22_OOP_Class_StaticFieldAndFunction {
    public static void main(String[] args) {
        System.out.println("静态方法");
        Person32 ming = new Person32("Xiao Ming", 12);
        Person32 hong = new Person32("Xiao Hong", 15);
        ming.number = 88;
        System.out.println(hong.number);
        hong.number = 99;
        System.out.println(ming.number);
        System.out.println("静态方法");
        Person33.setNumber(99);
        System.out.println(Person33.number);
    }
}
//静态字段
    //在一个class中定义的字段，我们称之为实例字段。实例字段的特点是，每个实例都有独立的字段，各个实例的同名字段互不影响。
    //
    //还有一种字段，是用static修饰的字段，称为静态字段：static field。
    //
    //实例字段在每个实例中都有自己的一个独立“空间”，但是静态字段只有一个共享“空间”，所有实例都会共享该字段。举个例子：
    class Person31 {
        public String name;
        public int age;
        // 定义静态字段number:
        public static int number;
    }
    //我们来看看下面的代码：
    class Person32 {
        public String name;
        public int age;

        public static int number;

        public Person32(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    /*
    对于静态字段，无论修改哪个实例的静态字段，效果都是一样的：所有实例的静态字段都被修改了，原因是静态字段并不属于实例：

            ┌──────────────────┐
    ming ──>│Person instance   │
            ├──────────────────┤
            │name = "Xiao Ming"│
            │age = 12          │
            │number ───────────┼──┐    ┌─────────────┐
            └──────────────────┘  │    │Person class │
                                  │    ├─────────────┤
                                  ├───>│number = 99  │
            ┌──────────────────┐  │    └─────────────┘
    hong ──>│Person instance   │  │
            ├──────────────────┤  │
            │name = "Xiao Hong"│  │
            │age = 15          │  │
            │number ───────────┼──┘
            └──────────────────┘
    虽然实例可以访问静态字段，但是它们指向的其实都是Person class的静态字段。所以，所有实例共享一个静态字段。

    因此，不推荐用实例变量.静态字段去访问静态字段，因为在Java程序中，实例对象并没有静态字段。
    在代码中，实例对象能访问静态字段只是因为编译器可以根据实例类型自动转换为类名.静态字段来访问静态对象。
     */
    //推荐用类名来访问静态字段。可以把静态字段理解为描述class本身的字段（非实例字段）。对于上面的代码，更好的写法是：
    //Person.number = 99;
    //System.out.println(Person.number);

//静态方法
    //有静态字段，就有静态方法。用static修饰的方法称为静态方法。

    class Person33 {
        public static int number;

        public static void setNumber(int value) {
            number = value;
        }
    }
    /*
    因为静态方法属于class而不属于实例，因此，静态方法内部，无法访问this变量，也无法访问实例字段，它只能访问静态字段。

    通过实例变量也可以调用静态方法，但这只是编译器自动帮我们把实例改写成类名而已。

    通常情况下，通过实例变量访问静态字段和静态方法，会得到一个编译警告。

    静态方法经常用于工具类。例如：

    Arrays.sort()

    Math.random()

    静态方法也经常用于辅助方法。注意到Java程序的入口main()也是静态方法。
     */

//接口的静态字段
    //因为interface是一个纯抽象类，所以它不能定义实例字段。但是，interface是可以有静态字段的，并且静态字段必须为final类型：
    interface Person34 {
        public static final int MALE = 1;
        public static final int FEMALE = 2;
    }

    //实际上，因为interface的字段只能是public static final类型，所以我们可以把这些修饰符都去掉，上述代码可以简写为：
    interface Person35 {
        // 编译器会自动加上public statc final:
        int MALE = 1;
        int FEMALE = 2;
    }
    //编译器会自动把该字段变为public static final类型。





















