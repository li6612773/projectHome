package com.sjli;

public class A22_OOP_Class_AbstractClass {
    public static void main(String[] args) {
        Person11 p = new Student11();
        p.run();
    }
}

//抽象类
    //由于多态的存在，每个子类都可以覆写父类的方法，例如：
    /*
    class Person {
        public void run() { … }
    }

    class Student extends Person {
        @Override
        public void run() { … }
    }

    class Teacher extends Person {
        @Override
        public void run() { … }
    }
    */
    //从Person类派生的Student和Teacher都可以覆写run()方法。
    //
    //如果父类Person的run()方法没有实际意义，能否去掉方法的执行语句？
    /*
    class Person {
    public void run(); // Compile Error!
    }
    */
    //答案是不行，会导致编译错误，因为定义方法的时候，必须实现方法的语句。
    //
    //能不能去掉父类的run()方法？
    //
    //答案还是不行，因为去掉父类的run()方法，就失去了多态的特性。例如，runTwice()就无法编译：
    //
    //public void runTwice(Person p) {
    //    p.run(); // Person没有run()方法，会导致编译错误
    //    p.run();
    //}
    //如果父类的方法本身不需要实现任何功能，仅仅是为了定义方法签名，目的是让子类去覆写它，那么，可以把父类的方法声明为抽象方法：
    /*
    class Person11 {
        public abstract void run();
    }*/

//抽象类定义
    //如果一个class定义了方法，但没有具体执行代码，这个方法就是抽象方法，抽象方法用abstract修饰。
    //
    //因为无法执行抽象方法，因此这个类也必须申明为抽象类（abstract class）。
    //
    //使用abstract修饰的类就是抽象类。我们无法实例化一个抽象类：
    //Person p = new Person(); // 编译错误
    //无法实例化的抽象类有什么用？
    //
    //因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。
    // 因此，抽象方法实际上相当于定义了“规范”。
    //
    //例如，Person类定义了抽象方法run()，那么，在实现子类Student的时候，就必须覆写run()方法：

    abstract class Person11 {
        public abstract void run();
    }

    class Student11 extends Person11 {
        @Override
        public void run() {
            System.out.println("Student.run");
        }
    }
//面向抽象编程
    //当我们定义了抽象类Person，以及具体的Student、Teacher子类的时候，我们可以通过抽象类Person类型去引用具体的子类的实例：
    //Person s = new Student();
    //Person t = new Teacher();
    //这种引用抽象类的好处在于，我们对其进行方法调用，并不关心Person类型变量的具体子类型：
    // 不关心Person变量的具体子类型:
    //s.run();
    //t.run();
    //同样的代码，如果引用的是一个新的子类，我们仍然不关心具体类型：
    // 同样不关心新的子类是如何实现run()方法的：
    //Person e = new Employee();
    //e.run();

    //这种尽量引用高层类型，避免引用实际子类型的方式，称之为面向抽象编程。
    //
    //面向抽象编程的本质就是：
    //
    //上层代码只定义规范（例如：abstract class Person）；
    //
    //不需要子类就可以实现业务逻辑（正常编译）；
    //
    //具体的业务逻辑由不同的子类实现，调用者并不关心。







