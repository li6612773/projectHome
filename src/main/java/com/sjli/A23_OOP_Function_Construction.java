package com.sjli;

public class A24_OOP_Function_Construction {
    public static void main(String[] args) {
    //构造方法
        //创建实例的时候，我们经常需要同时初始化这个实例的字段，例如：
        //
        //Person ming = new Person();
        //ming.setName("小明");
        //ming.setAge(12);
        //初始化对象实例需要3行代码，而且，如果忘了调用setName()或者setAge()，这个实例内部的状态就是不正确的。
        //
        //能否在创建对象实例时就把内部字段全部初始化为合适的值？
        //
        //完全可以。
        //
        //这时，我们就需要构造方法。

        //创建实例的时候，实际上是通过构造方法来初始化实例的。我们先来定义一个构造方法，能在创建Person实例的时候，
        // 一次性传入name和age，完成初始化：
        Person p = new Person("Xiao Ming", 15);
        System.out.println(p.getName());
        System.out.println(p.getAge());

    //默认构造方法
        //是不是任何class都有构造方法？是的。
        //
        //那前面我们并没有为Person类编写构造方法，为什么可以调用new Person()？
        //
        //原因是如果一个类没有定义构造方法，编译器会自动为我们生成一个默认构造方法，它没有参数，也没有执行语句，类似这样：
        /*
        class Person {
            public Person() {
            }
        }
        */
        //要特别注意的是，如果我们自定义了一个构造方法，那么，编译器就不再自动创建默认构造方法：
        /*
        public class Main {
            public static void main(String[] args) {
                Person p = new Person(); // 编译错误:找不到这个构造方法
            }
        }
        */

        //如果既要能使用带参数的构造方法，又想保留不带参数的构造方法，那么只能把两个构造方法都定义出来：
        Person3 p1 = new Person3("Xiao Ming", 15); // 既可以调用带参数的构造方法
        Person3 p2 = new Person3(); // 也可以调用无参数构造方法

        //没有在构造方法中初始化字段时，引用类型的字段默认是null，数值类型的字段用默认值，int类型默认值是0，布尔类型默认值是false：
        /*
        class Person {
            private String name; // 默认初始化为null
            private int age; // 默认初始化为0

            public Person() {
            }
        }
        */
        //也可以对字段直接进行初始化：
        /*
        class Person {
            private String name = "Unamed";
            private int age = 10;
        }
        */
        //在Java中，创建对象实例的时候，按照如下顺序进行初始化：
        //
        //先初始化字段，例如，int age = 10;表示字段初始化为10，double salary;表示字段默认初始化为0，String name;表示引用类型字段默认初始化为null；
        //
        //执行构造方法的代码进行初始化

    //多构造方法
        //可以定义多个构造方法，在通过new操作符调用的时候，编译器通过构造方法的参数数量、位置和类型自动区分：
        Person4 p4 = new Person4("Xiao Ming", 20);//会自动匹配到构造方法public Person(String, int)。
        System.out.println(p4.getAge()+p4.getName());
    }
}

//多构造方法
//如果调用new Person("Xiao Ming", 20);，会自动匹配到构造方法public Person(String, int)。
//
//如果调用new Person("Xiao Ming");，会自动匹配到构造方法public Person(String)。
//
//如果调用new Person();，会自动匹配到构造方法public Person()。
class Person4 {
    private String name;
    private int age;

    public Person4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person4(String name) {
        this.name = name;
        this.age = 12;
    }
    //一个构造方法可以调用其他构造方法，这样做的目的是便于代码复用。调用其他构造方法的语法是this(…)：
    public Person4() {
        this("sjli");
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}


class Person3 {
    private String name;
    private int age;

    public Person3() {
    }

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}