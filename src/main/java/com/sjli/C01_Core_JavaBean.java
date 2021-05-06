package com.sjli;

public class C01_Core_JavaBean {
    public static void main(String[] args) {
        //JavaBean
        //在Java中，有很多class的定义都符合这样的规范：
        //
        //若干private实例字段；
        //通过public方法来读写实例字段。
        /*
        例如：

        public class Person {
            private String name;
            private int age;

            public String getName() { return this.name; }
            public void setName(String name) { this.name = name; }

            public int getAge() { return this.age; }
            public void setAge(int age) { this.age = age; }
        }
         */
        //如果读写方法符合以下这种命名规范：
        //// 读方法:
        //public Type getXyz()
        //// 写方法:
        //public void setXyz(Type value)
        //那么这种class被称为JavaBean：
        //上面的字段是xyz，那么读写方法名分别以get和set开头，
        // 并且后接大写字母开头的字段名Xyz，因此两个读写方法名分别是getXyz()和setXyz()。
        //
        //boolean字段比较特殊，它的读方法一般命名为isXyz()：
        //// 读方法:
        //public boolean isChild()
        //// 写方法:
        //public void setChild(boolean value)
        /*
        我们通常把一组对应的读方法（getter）和写方法（setter）称为属性（property）。例如，name属性：

        对应的读方法是String getName()
        对应的写方法是setName(String)
        只有getter的属性称为只读属性（read-only），例如，定义一个age只读属性：

        对应的读方法是int getAge()
        无对应的写方法setAge(int)
        类似的，只有setter的属性称为只写属性（write-only）。

        很明显，只读属性很常见，只写属性不常见。

        属性只需要定义getter和setter方法，不一定需要对应的字段。例如，child只读属性定义如下：
        public class Person {
            private String name;
            private int age;

            public String getName() { return this.name; }
            public void setName(String name) { this.name = name; }

            public int getAge() { return this.age; }
            public void setAge(int age) { this.age = age; }

            public boolean isChild() {
                return age <= 6;
            }
        }
        可以看出，getter和setter也是一种数据封装的方法。
         */
    //JavaBean的作用
        //JavaBean主要用来传递数据，即把一组数据组合成一个JavaBean便于传输。
        // 此外，JavaBean可以方便地被IDE工具分析，生成读写属性的代码，主要用在图形界面的可视化设计中。
        //
        //通过IDE，可以快速生成getter和setter。例如，在Eclipse中，先输入以下代码：
        /*
        public class Person {
            private String name;
            private int age;
        }
        然后，点击右键，在弹出的菜单中选择“Source”，“Generate Getters and Setters”，
        在弹出的对话框中选中需要生成getter和setter方法的字段，点击确定即可由IDE自动完成所有方法代码。
         */
    //枚举JavaBean属性
        //要枚举一个JavaBean的所有属性，可以直接使用Java核心库提供的Introspector：
        /*
        public class Main {
            public static void main(String[] args) throws Exception {
                BeanInfo info = Introspector.getBeanInfo(Person.class);
                for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                    System.out.println(pd.getName());
                    System.out.println("  " + pd.getReadMethod());
                    System.out.println("  " + pd.getWriteMethod());
                }
            }
        }

        class Person {
            private String name;
            private int age;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
        运行上述代码，可以列出所有的属性，以及对应的读写方法。注意class属性是从Object继承的getClass()方法带来的。
         */
    }
}
