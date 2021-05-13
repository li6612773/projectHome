package com.sjli.basis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class A22_OOP_Class_Interface {
    public static void main(String[] args) {
        Person24 p = new Student24("Xiao Ming");
        p.run();
    }
}

//如果一个抽象类没有字段，所有方法全部都是抽象方法：

abstract class Person21{
    public abstract void run();
    public abstract String getName();
}
//就可以把该抽象类改写为接口：interface。
//在Java中，使用interface可以声明一个接口：
interface Person22{
    void run();
    String getName();
}
//所谓interface，就是比抽象类还要抽象的纯抽象接口，因为它连字段都不能有。
// 因为接口定义的所有方法默认都是public abstract的，所以这两个修饰符不需要写出来（写不写效果都一样）。

//当一个具体的class去实现一个interface时，需要使用implements关键字。举个例子：
class Student22 implements Person22{
    protected String name;
    public Student22(String name){
        this.name = name;
    }
    @Override
    public void run(){
        System.out.println(this.name + "run");
    }
    @Override
    public String getName(){
        return this.name;
    }

}

//我们知道，在Java中，一个类只能继承自另一个类，不能从多个类继承。但是，一个类可以实现多个interface，例如：
/*class Student implements Person, Hello { // 实现了两个interface
    ...
}*/

//术语
/*      注意区分术语：

        Java的接口特指interface的定义，表示一个接口类型和一组方法签名，而编程接口泛指接口规范，如方法签名，
        数据格式，网络协议等。

        抽象类和接口的对比如下：

                             abstract class	        interface
                继承	         只能extends一个class 	可以implements多个interface
                字段	         可以定义实例字段	        不能定义实例字段
                抽象方法    	 可以定义抽象方法    	    可以定义抽象方法
                非抽象方法	 可以定义非抽象方法	    可以定义default方法
*/


//接口继承
//一个interface可以继承自另一个interface。interface继承自interface使用extends，它相当于扩展了接口的方法。例如：
interface Hello23 {
    void hello23();
}

interface Person23 extends Hello23 {
    void run();
    String getName();
}

//继承关系
//合理设计interface和abstract class的继承关系，可以充分复用代码。
// 一般来说，公共逻辑适合放在abstract class中，具体逻辑放到各个子类，而接口层次代表抽象程度。
// 可以参考Java的集合类定义的一组接口、抽象类以及具体子类的继承关系：

/*
┌───────────────┐
│   Iterable    │
└───────────────┘
        ▲                ┌───────────────────┐
        │                │      Object       │
┌───────────────┐        └───────────────────┘
│  Collection   │                  ▲
└───────────────┘                  │
        ▲     ▲          ┌───────────────────┐
        │     └──────────│AbstractCollection │
┌───────────────┐        └───────────────────┘
│     List      │                  ▲
└───────────────┘                  │
              ▲          ┌───────────────────┐
              └──────────│   AbstractList    │
                         └───────────────────┘
                                ▲     ▲
                                │     │
                                │     │
                     ┌────────────┐ ┌────────────┐
                     │ ArrayList  │ │ LinkedList │
                     └────────────┘ └────────────┘
 */
//在使用的时候，实例化的对象永远只能是某个具体的子类，但总是通过接口去引用它，因为接口比抽象类更抽象：
class samp1 {
    List list = new ArrayList(); // 用List接口引用具体子类的实例
    Collection coll = list; // 向上转型为Collection接口
    Iterable it = coll; // 向上转型为Iterable接口

}

//default方法
//在接口中，可以定义default方法。例如，把Person接口的run()方法改为default方法：
//public class Main {
//    public static void main(String[] args) {
//        Person p = new Student("Xiao Ming");
//        p.run();
//    }
//}

interface Person24 {
    String getName();
    default void run() {
        System.out.println(getName() + " run");
    }
}

class Student24 implements Person24 {
    private String name;

    public Student24(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
//实现类可以不必覆写default方法。default方法的目的是，当我们需要给接口新增一个方法时，会涉及到修改全部子类。
// 如果新增的是default方法，那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
//
//default方法和抽象类的普通方法是有所不同的。因为interface没有字段，default方法无法访问字段，
// 而抽象类的普通方法可以访问实例字段。

























