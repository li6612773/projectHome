package com.sjli.basis;

public class A22_OOP_Class_Polymorphism {
    public static void main(String[] args) {
        Person5 p = new Student5();
        p.run();//应该打印Person.run还是Student.run?
        //那么，一个实际类型为Student，引用类型为Person的变量，调用其run()方法，调用的是Person还是Student的run()方法？
        //
        //运行一下上面的代码就可以知道，实际上调用的方法是Student的run()方法。因此可得出结论：
        //
        //Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
        //
        //这个非常重要的特性在面向对象编程中称之为多态。它的英文拼写非常复杂：Polymorphic。

        //现在，我们要编写一个报税的财务软件，对于一个人的所有收入进行报税，可以这么写：
        // 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税:
        Income[] incomes = new Income[] {
                new Income(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));
        //观察totalTax()方法：利用多态，totalTax()方法只需要和Income打交道，它完全不需要知道Salary和StateCouncilSpecialAllowance的存在，就可以正确计算出总的税。如果我们要新增一种稿费收入，只需要从Income派生，然后正确覆写getTax()方法就可以。把新的类型传入totalTax()，不需要修改任何代码。
        //
        //可见，多态具有一个非常强大的功能，就是允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码。

    }

    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income: incomes) {
            total = total + income.getTax();
        }
        return total;
    }
}
//多态
    //在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）。
    //例如，在Person类中，我们定义了run()方法：
    class Person5 {
        public void run() {
            System.out.println("Person5.run");
        }
    }
    //在子类Student中，覆写这个run()方法：
    class Student5 extends Person5 {
        @Override
        public void run() {
            System.out.println("Student5.run");
        }
    }
    //Override和Overload不同的是，如果方法签名如果不同，就是Overload，Overload方法是一个新方法；
    // 如果方法签名相同，并且返回值也相同，就是Override。
    // 注意：方法名相同，方法参数相同，但方法返回值不同，也是不同的方法。在Java程序中，出现这种情况，编译器会报错。
    //加上@Override可以让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错。
    //但是@Override不是必需的。

//多态定义：
    //多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。例如：
    //
    //Person p = new Student();
    //p.run(); // 无法确定运行时究竟调用哪个run()方法
    //有童鞋会问，从上面的代码一看就明白，肯定调用的是Student的run()方法啊。
    //
    //但是，假设我们编写这样一个方法：
    //
    //public void runTwice(Person p) {
    //    p.run();
    //    p.run();
    //}
    //它传入的参数类型是Person，我们是无法知道传入的参数实际类型究竟是Person，还是Student，还是Person的其他子类，
    // 因此，也无法确定调用的是不是Person类定义的run()方法。
    //
    //所以，多态的特性就是，运行期才能动态决定调用的子类方法。对某个类型调用某个方法，
    // 执行的实际方法可能是某个子类的覆写方法。这种不确定性的方法调用，究竟有什么作用？
//我们还是来举栗子。
    //假设我们定义一种收入，需要给它报税，那么先定义一个Income类：
    class Income {
        protected double income;

        public Income(double income) {
            this.income = income;
        }
        public double getTax() {
            return income * 0.1; // 税率10%
        }
    }
    //对于工资收入，可以减去一个基数，那么我们可以从Income派生出SalaryIncome，并覆写getTax()：
    class Salary extends Income {
        public Salary(double income) {
            super(income);
        }

        @Override
        public double getTax() {
            if (income <= 5000) {
                return 0;
            }
            return (income - 5000) * 0.2;
        }
    }
    //如果你享受国务院特殊津贴，那么按照规定，可以全部免税：
    class StateCouncilSpecialAllowance extends Income {
        public StateCouncilSpecialAllowance(double income) {
            super(income);
        }

        @Override
        public double getTax() {
            return 0;
        }
    }

//覆写Object方法
    //因为所有的class最终都继承自Object，而Object定义了几个重要的方法：
    //
    //toString()：把instance输出为String；
    //equals()：判断两个instance是否逻辑相等；
    //hashCode()：计算一个instance的哈希值。
    //在必要的情况下，我们可以覆写Object的这几个方法。例如：
    class Person6 {
        private String name ;

        Person6(String name) {
            this.name = name;
        }

        // 显示更有意义的字符串:
        @Override
        public String toString() {
            return "Person:name=" + name;
        }

        // 比较是否相等:
        @Override
        public boolean equals(Object o) {
            // 当且仅当o为Person6类型:
            if (o instanceof Person6) {
                Person6 p = (Person6) o;
                // 并且name字段相同时，返回true:
                return this.name.equals(p.name);
            }
            return false;
        }

        // 计算hash:
        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
    }

//调用super
    //在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用。例如：
    class Person7 {
        protected String name;
        public String hello() {
            return "Hello, " + name;
        }
    }

    class Student7 extends Person7 {
        @Override
        public String hello() {
            // 调用父类的hello()方法:
            return super.hello() + "!";
        }
    }

//final
    //在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用。例如：
    class Person8 {
        protected String name;
        public final String hello() {
            return "Hello, " + name;
        }
    }

    class Student8 extends Person8 {
        // compile error: 不允许覆写
        // @Override
        //public String hello() {
        // 调用父类的hello()方法:
        //return super.hello() + "!";
        //}
    }
    //如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为final。用final修饰的类不能被继承：
    final class Person9 {
        protected String name;
    }

    // compile error: 不允许继承自Person
    //Student9 extends Person9 {
    //}

    //对于一个类的实例字段，同样可以用final修饰。用final修饰的字段在初始化后不能被修改。例如：
    class Person10 {
        public final String name = "Unnamed";
    }
    //对final字段重新赋值会报错：
    //
    //Person p = new Person();
    //p.name = "New Name"; // compile error!
    //可以在构造方法中初始化final字段：
    //class Person {
    //    public final String name;
    //    public Person(String name) {
    //        this.name = name;
    //    }
    //}
    // 这种方法更为常用，因为可以保证实例一旦创建，其final字段就不可修改。




