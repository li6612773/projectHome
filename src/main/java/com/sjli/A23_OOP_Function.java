package com.sjli;

public class A23_OOP_Function {
    public static void main(String[] args) {
    //方法
        //一个class可以包含多个field，例如，我们给Person类就定义了两个field：
        class Person {
            public String name;
            public int age;
        }
        //但是，直接把field用public暴露给外部可能会破坏封装性。比如，代码可以这样写：
        Person ming = new Person();
        ming.name = "Xiao Ming";
        ming.age = -99; // age设置为负数

        //把field从public改成private，外部代码不能访问这些field，那我们定义这些field有什么用？怎么才能给它赋值？怎么才能读取它的值？
        //
        //所以我们需要使用方法（method）来让外部代码可以间接修改field：
        Person2 ming2 = new Person2();
        ming2.setName("Xiao Ming"); // 设置name
        ming2.setAge(12); // 设置age
        System.out.println(ming2.getName() + ", " + ming2.getAge());
        //虽然外部代码不能直接修改private字段，
        // 但是，外部代码可以调用方法setName()和setAge()来间接修改private字段。在方法内部，我们就有机会检查参数对不对。
        // 比如，setAge()就会检查传入的参数，参数超出了范围，直接报错。这样，外部代码就没有任何机会把age设置成不合理的值。
    //定义方法
        /*方法返回值通过return语句实现，如果没有返回值，返回类型设置为void，可以省略return。
        修饰符 方法返回类型 方法名(方法参数列表) {
            若干方法语句;
            return 方法返回值;
        }
        */
    }
}

class Person2 {
    private String name;
    private int age;
    private int birth;

    public String getName() {
    //this变量
        //在方法内部，可以使用一个隐含的变量this，它始终指向当前实例。因此，通过this.field就可以访问当前实例的字段。
        //
        //如果没有命名冲突，可以省略this。例如：
        //但是，如果有局部变量和字段重名，那么局部变量优先级更高，就必须加上this,如setName函数内不两个name变量
        return this.name;
    }

    public void setName(String name) {
        //对setName()方法同样可以做检查，例如，不允许传入null和空字符串：
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("invalid name");
        }
        this.name = name.strip(); // 去掉首尾空格
    }

    public int getAge() {
        return calcAge(2019); // 调用private方法
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    // private方法:
        //有public方法，自然就有private方法。和private字段一样，private方法不允许外部调用，那我们定义private方法有什么用？
        //
        //定义private方法的理由是内部方法是可以调用private方法的。例如：
    private int calcAge(int currentYear) {
        return currentYear - this.birth;
    }

    //方法参数
        //方法可以包含0个或任意个参数。方法参数用于接收传递给方法的变量值。调用方法时，必须严格按照参数的定义一一传递。例如：
    public void setNameAndAge(String name, int age) {
        this.name = name;
        this.age = age;
        //调用这个setNameAndAge()方法时，必须有两个参数，且第一个参数必须为String，第二个参数必须为int：
        //
        //Person ming = new Person();
        //ming.setNameAndAge("Xiao Ming"); // 编译错误：参数个数不对
        //ming.setNameAndAge(12, "Xiao Ming"); // 编译错误：参数类型不对
    }
}