package com.sjli.basis;

public class A22_OOP_Class {
    public static void main(String[] args) {
    //面向对象基础
        /*
        面向对象编程，是一种通过对象的方式，把现实世界映射到计算机模型的一种编程方法。

        现实世界中，我们定义了“人”这种抽象概念，而具体的人则是“小明”、“小红”、“小军”等一个个具体的人。
        所以，“人”可以定义为一个类（class），而具体的人则是实例（instance）：

        现实世界	 计算机模型	Java代码
        人	类   /  class	class Person { }
        小明	实例 /  ming	    Person ming = new Person()
        小红	实例 /  hong   	Person hong = new Person()
        小军	实例 /  jun    	Person jun = new Person()
        同样的，“书”也是一种抽象的概念，所以它是类，而《Java核心技术》、《Java编程思想》、《Java学习笔记》则是实例：

        现实世界	计算机模型	Java代码
        书	类 / class	class Book { }
        Java核心技术	实例 / book1	Book book1 = new Book()
        Java编程思想	实例 / book2	Book book2 = new Book()
        Java学习笔记	实例 / book3	Book book3 = new Book()
    //class和instance
        所以，只要理解了class和instance的概念，基本上就明白了什么是面向对象编程。

        class是一种对象模版，它定义了如何创建实例，因此，class本身就是一种数据类型：

        class

        而instance是对象实例，instance是根据class创建的实例，可以创建多个instance，每个instance类型相同，但各自属性可能不相同：
        */
    //定义class
        class Person {
            public String name;
            public int age;
        }
        /*一个class可以包含多个字段（field），字段用来描述一个类的特征。上面的Person类，
        我们定义了两个字段，一个是String类型的字段，命名为name，一个是int类型的字段，命名为age。
        因此，通过class，把一组数据汇集到一个对象上，实现了数据封装。

        public是用来修饰字段的，它表示这个字段可以被外部访问。*/
        //我们再看另一个Book类的定义：
        class Book {
            public String name;
            public String author;
            public String isbn;
            public double price;
        }

    //创建实例
        //定义了class，只是定义了对象模版，而要根据对象模版创建出真正的对象实例，必须用new操作符。
        //
        //new操作符可以创建一个实例，然后，我们需要定义一个引用类型的变量来指向这个实例：
        Person ming = new Person();
        //上述代码创建了一个Person类型的实例，并通过变量ming指向它。
        //
        //注意区分Person ming是定义Person类型的变量ming，而new Person()是创建Person实例。
        //
        //有了指向这个实例的变量，我们就可以通过这个变量来操作实例。访问实例变量可以用变量.字段，例如
        ming.name = "Xiao Ming"; // 对字段name赋值
        ming.age = 12; // 对字段age赋值
        System.out.println(ming.name); // 访问字段name

        Person hong = new Person();
        hong.name = "Xiao Hong";
        hong.age = 15;
        /*上述两个变量分别指向两个不同的实例，它们在内存中的结构如下：

                    ┌──────────────────┐
        ming ──────>│Person instance   │
                    ├──────────────────┤
                    │name = "Xiao Ming"│
                    │age = 12          │
                    └──────────────────┘
                    ┌──────────────────┐
        hong ──────>│Person instance   │
                    ├──────────────────┤
                    │name = "Xiao Hong"│
                    │age = 15          │
                    └──────────────────┘
        两个instance拥有class定义的name和age字段，且各自都有一份独立的数据，互不干扰。*/

    }
}
