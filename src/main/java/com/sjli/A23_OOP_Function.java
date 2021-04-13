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
    }

    static class Person2 {
        private String name;
        private int age;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            if (age < 0 || age > 100) {
                throw new IllegalArgumentException("invalid age value");
            }
            this.age = age;
        }
    }
}
