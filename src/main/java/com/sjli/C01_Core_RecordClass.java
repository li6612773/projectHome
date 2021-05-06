package com.sjli;

public class C01_Core_RecordClass {
    public static void main(String[] args) {
    //记录类
        //使用String、Integer等类型的时候，这些类型都是不变类，一个不变类具有以下特点：
        //
        //定义class时使用final，无法派生子类；
        //每个字段使用final，保证创建实例后无法修改任何字段。
        //假设我们希望定义一个Point类，有x、y两个变量，同时它是一个不变类，可以这么写：
        /*
        final class Point {
            private final int x;
            private final int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int x() {
                return this.x;
            }

            public int y() {
                return this.y;
            }
        }
         */
        //为了保证不变类的比较，还需要正确覆写equals()和hashCode()方法，这样才能在集合类中正常使用。
        // 后续我们会详细讲解正确覆写equals()和hashCode()，这里演示Point不变类的写法目的是，
        // 这些代码写起来都非常简单，但是很繁琐。
    //record
        //从Java 14开始，引入了新的Record类。我们定义Record类时，使用关键字record。
        // 把上述Point类改写为Record类，代码如下：
        /*
        Point p = new Point(123, 456);
        System.out.println(p.x());
        System.out.println(p.y());
        System.out.println(p);
        public record Point(int x, int y) {
        }
         */
        /*
        public final class Point extends Record {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return this.x;
        }

        public int y() {
            return this.y;
        }

        public String toString() {
            return String.format("Point[x=%s, y=%s]", x, y);
        }

        public boolean equals(Object o) {
            ...
        }
        public int hashCode() {
            ...
        }
        }
        除了用final修饰class以及每个字段外，编译器还自动为我们创建了构造方法，和字段名同名的方法，以及覆写toString()、equals()和hashCode()方法。

        换句话说，使用record关键字，可以一行写出一个不变类。

        和enum类似，我们自己不能直接从Record派生，只能通过record关键字由编译器实现继承。

        构造方法
        编译器默认按照record声明的变量顺序自动创建一个构造方法，并在方法内给字段赋值。那么问题来了，如果我们要检查参数，应该怎么办？

        假设Point类的x、y不允许负数，我们就得给Point的构造方法加上检查逻辑：

        public record Point(int x, int y) {
            public Point {
                if (x < 0 || y < 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        注意到方法public Point {...}被称为Compact Constructor，它的目的是让我们编写检查逻辑，编译器最终生成的构造方法如下：

        public final class Point extends Record {
            public Point(int x, int y) {
                // 这是我们编写的Compact Constructor:
                if (x < 0 || y < 0) {
                    throw new IllegalArgumentException();
                }
                // 这是编译器继续生成的赋值代码:
                this.x = x;
                this.y = y;
            }
            ...
        }
        作为record的Point仍然可以添加静态方法。一种常用的静态方法是of()方法，用来创建Point：

        public record Point(int x, int y) {
            public static Point of() {
                return new Point(0, 0);
            }
            public static Point of(int x, int y) {
                return new Point(x, y);
            }
        }
        这样我们可以写出更简洁的代码：

        var z = Point.of();
        var p = Point.of(123, 456);
         */


    }
}

