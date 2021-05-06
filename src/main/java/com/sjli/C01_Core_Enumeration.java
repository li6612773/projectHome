package com.sjli;

public class C01_Core_Enumeration {
    public static void main(String[] args) {
    //枚举类
        //在Java中，我们可以通过static final来定义常量。
        // 例如，我们希望定义周一到周日这7个常量，可以用7个不同的int表示：
        class Weekday {
            public static final int SUN = 0;
            public static final int MON = 1;
            public static final int TUE = 2;
            public static final int WED = 3;
            public static final int THU = 4;
            public static final int FRI = 5;
            public static final int SAT = 6;
        }
        //使用常量的时候，可以这么引用：
        int day = 1;
        if (day == Weekday.SAT || day == Weekday.SUN) {
            // TODO: work at home
        }
        //也可以把常量定义为字符串类型，例如，定义3种颜色的常量：
        class Color {
            public static final String RED = "r";
            public static final String GREEN = "g";
            public static final String BLUE = "b";
        }
        //使用常量的时候，可以这么引用：
        String color = "r" ;
        if (Color.RED.equals(color)) {
            // TODO:
        }
        //无论是int常量还是String常量，使用这些常量来表示一组枚举值的时候，
        // 有一个严重的问题就是，编译器无法检查每个值的合理性。例如：
        int weekday = 1;
        int tasks = 2;
        if (weekday == 6 || weekday == 7) {
            if (tasks == Weekday.MON) {
                // TODO:
            }
        }
        //上述代码编译和运行均不会报错，但存在两个问题：
        //
        //注意到Weekday定义的常量范围是0~6，并不包含7，编译器无法检查不在枚举中的int值；
        //定义的常量仍可与其他变量比较，但其用途并非是枚举星期值。
    //enum
        //为了让编译器能自动检查某个值在枚举的集合内，并且，不同用途的枚举需要不同的类型来标记，
        // 不能混用，我们可以使用enum来定义枚举类：
        Weekday2 day2 = Weekday2.SUN;
        if (day2 == Weekday2.SAT || day2 == Weekday2.SUN) {
            System.out.println("Work at home!");
        } else {
            System.out.println("Work at office!");
        }
        //注意到定义枚举类是通过关键字enum实现的，我们只需依次列出枚举的常量名。
        //
        //和int定义的常量相比，使用enum定义枚举有如下好处：
        //
        //首先，enum常量本身带有类型信息，即Weekday.SUN类型是Weekday，
        // 编译器会自动检查出类型错误。例如，下面的语句不可能编译通过：
        //int day = 1;
        //if (day == Weekday.SUN) { // Compile error: bad operand types for binary operator '=='
        //}
        //其次，不可能引用到非枚举的值，因为无法通过编译。
        //
        //最后，不同类型的枚举不能互相比较或者赋值，因为类型不符。
        // 例如，不能给一个Weekday枚举类型的变量赋值为Color枚举类型的值：
        Weekday2 x = Weekday2.SUN; // ok!
        //Weekday2 y = Color.RED; // Compile error: incompatible types
        //这就使得编译器可以在编译期自动检查出所有可能的潜在错误。
    //enum的比较
        //使用enum定义的枚举类是一种引用类型。前面我们讲到，引用类型比较，
        // 要使用equals()方法，如果使用==比较，它比较的是两个引用类型的变量是否是同一个对象
        // 。因此，引用类型比较，要始终使用equals()方法，但enum类型可以例外。
        //
        //这是因为enum类型的每个常量在JVM中只有一个唯一实例，所以可以直接用==比较：
        if (day2 == Weekday2.FRI) { // ok!
        }
        if (day2.equals(Weekday2.SUN)) { // ok, but more code!
        }
    //enum类型
        //通过enum定义的枚举类，和其他的class有什么区别？
        //
        //答案是没有任何区别。enum定义的类型就是class，只不过它有以下几个特点：
        //
        //定义的enum类型总是继承自java.lang.Enum，且无法被继承；
        //只能定义出enum的实例，而无法通过new操作符创建enum的实例；
        //定义的每个实例都是引用类型的唯一实例；
        //可以将enum类型用于switch语句。
        //例如，我们定义的Color枚举类：
        /*
        enum Color {
            RED, GREEN, BLUE;
        }
         */
        //编译器编译出的class大概就像这样：
        /*
        public final class Color extends Enum { // 继承自Enum，标记为final class
            // 每个实例均为全局唯一:
            public static final Color RED = new Color();
            public static final Color GREEN = new Color();
            public static final Color BLUE = new Color();
            // private构造方法，确保外部无法调用new操作符:
            private Color() {}
        }
         */
        //所以，编译后的enum类和普通class并没有任何区别。
        // 但是我们自己无法按定义普通class那样来定义enum，必须使用enum关键字，这是Java语法规定的。
        //
        //因为enum是一个class，每个枚举的值都是class实例，因此，这些实例有一些方法：
        //name()
        //返回常量名，例如：
        //
        String s = Weekday2.SUN.name(); // "SUN"
        //ordinal()
        //返回定义的常量的顺序，从0开始计数，例如：
        //
        int n = Weekday2.MON.ordinal(); // 1
        //改变枚举常量定义的顺序就会导致ordinal()返回值发生变化。例如：
        //
        //public enum Weekday {
        //    SUN, MON, TUE, WED, THU, FRI, SAT;
        //}
        //和
        //
        //public enum Weekday {
        //    MON, TUE, WED, THU, FRI, SAT, SUN;
        //}
        //的ordinal就是不同的。如果在代码中编写了类似if(x.ordinal()==1)这样的语句，
        // 就要保证enum的枚举顺序不能变。新增的常量必须放在最后。
        //有些童鞋会想，Weekday的枚举常量如果要和int转换，使用ordinal()不是非常方便？比如这样写：
        String task = Weekday2.MON.ordinal() + "/ppt";
        //saveToFile(task);
        //但是，如果不小心修改了枚举的顺序，编译器是无法检查出这种逻辑错误的。
        // 要编写健壮的代码，就不要依靠ordinal()的返回值。因为enum本身是class，
        // 所以我们可以定义private的构造方法，并且，给每个枚举常量添加字段：
        /*
        public class Main {
            public static void main(String[] args) {
                Weekday day = Weekday.SUN;
                if (day.dayValue == 6 || day.dayValue == 0) {
                    System.out.println("Work at home!");
                } else {
                    System.out.println("Work at office!");
                }
            }
        }

        enum Weekday {
            MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(0);

            public final int dayValue;

            private Weekday(int dayValue) {
                this.dayValue = dayValue;
            }
        }

         */
        //这样就无需担心顺序的变化，新增枚举常量时，也需要指定一个int值。
        //
        // 注意：枚举类的字段也可以是非final类型，即可以在运行期修改，但是不推荐这样做！
        //默认情况下，对枚举常量调用toString()会返回和name()一样的字符串。
        // 但是，toString()可以被覆写，而name()则不行。我们可以给Weekday添加toString()方法：
        /*
        public class Main {
            public static void main(String[] args) {
                Weekday day = Weekday.SUN;
                if (day.dayValue == 6 || day.dayValue == 0) {
                    System.out.println("Today is " + day + ". Work at home!");
                } else {
                    System.out.println("Today is " + day + ". Work at office!");
                }
            }
        }

        enum Weekday {
            MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

            public final int dayValue;
            private final String chinese;

            private Weekday(int dayValue, String chinese) {
                this.dayValue = dayValue;
                this.chinese = chinese;
            }

            @Override
            public String toString() {
                return this.chinese;
            }
        }
         */
        //覆写toString()的目的是在输出时更有可读性。
        //
        // 注意：判断枚举常量的名字，要始终使用name()方法，绝不能调用toString()！
        //switch
        //最后，枚举类可以应用在switch语句中。因为枚举类天生具有类型信息和有限个枚举常量，
        // 所以比int、String类型更适合用在switch语句中：
        Weekday2 day3 = Weekday2.SUN;
        switch(day3) {
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.println("Today is " + day + ". Work at office!");
                break;
            case SAT:
            case SUN:
                System.out.println("Today is " + day + ". Work at home!");
                break;
            default:
                throw new RuntimeException("cannot process " + day);
        }
        //加上default语句，可以在漏写某个枚举常量时自动报错，从而及时发现错误。
    }
}
enum Weekday2 {
    FRI, MON, SAT, SUN, THU, TUE, WED;
}
enum Color {
    RED, GREEN, BLUE;
}