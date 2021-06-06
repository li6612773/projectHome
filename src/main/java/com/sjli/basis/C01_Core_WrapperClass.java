package com.sjli.basis;

public class C01_Core_WrapperClass {
    public static void main(String[] args) {
        //包装类型
        /*
        我们已经知道，Java的数据类型分两种：

        基本类型：byte，short，int，long，boolean，float，double，char

        引用类型：所有class和interface类型

        引用类型可以赋值为null，表示空，但基本类型不能赋值为null：
         */
        String s = null;
        //int n = null; // compile error!
        //那么，如何把一个基本类型视为对象（引用类型）？
        //
        //比如，想要把int基本类型变成一个引用类型，
        // 我们可以定义一个Integer类，它只包含一个实例字段int，
        // 这样，Integer类就可以视为int的包装类（Wrapper Class）：
        /*
        public class Integer {
            private int value;

            public Integer(int value) {
                this.value = value;
            }

            public int intValue() {
                return this.value;
            }
        }
         */
        //定义好了Integer类，我们就可以把int和Integer互相转换：
        /*
        Integer n = null;
        Integer n2 = new Integer(99);
        int n3 = n2.intValue();
         */
        //实际上，因为包装类型非常有用，Java核心库为每种基本类型都提供了对应的包装类型：
        /*
        基本类型  对应的引用类型
        boolean	java.lang.Boolean
        byte	java.lang.Byte
        short	java.lang.Short
        int	    java.lang.Integer
        long	java.lang.Long
        float	java.lang.Float
        double	java.lang.Double
        char	java.lang.Character
         */

        //我们可以直接使用，并不需要自己去定义：
        int i = 100;
        // 通过new操作符创建Integer实例(不推荐使用,会有编译警告):
        Integer n1 = new Integer(i);
        // 通过静态方法valueOf(int)创建Integer实例:
        Integer n2 = Integer.valueOf(i);
        // 通过静态方法valueOf(String)创建Integer实例:
        Integer n3 = Integer.valueOf("100");
        System.out.println(n3.intValue());

    //Auto Boxing 自动装箱
        //因为int和Integer可以互相转换：
        int i2 = 100;
        Integer n = Integer.valueOf(i2);
        int x = n.intValue();
        //所以，Java编译器可以帮助我们自动在int和Integer之间转型：
        Integer n5 = 100; // 编译器自动使用Integer.valueOf(int)
        int x5= n5; // 编译器自动使用Integer.intValue()
        //这种直接把int变为Integer的赋值写法，称为自动装箱（Auto Boxing），
        // 反过来，把Integer变为int的赋值写法，称为自动拆箱（Auto Unboxing）。
        //
        //注意：自动装箱和自动拆箱只发生在编译阶段，目的是为了少写代码。
        //
        //装箱和拆箱会影响代码的执行效率，因为编译后的class代码是严格区分基本类型和引用类型的。
        // 并且，自动拆箱执行时可能会报NullPointerException：
        Integer n6 = null;
        //int i6 = n6;

    //不变类
        //所有的包装类型都是不变类。我们查看Integer的源码可知，它的核心代码如下：
        /*
        public final class Integer {
            private final int value;
        }
         */
        //因此，一旦创建了Integer对象，该对象就是不变的。
        //
        //对两个Integer实例进行比较要特别注意：
        // 绝对不能用==比较，因为Integer是引用类型，必须使用equals()比较：
        Integer x7 = 127;
        Integer y7 = 127;
        Integer m7 = 99999;
        Integer n7 = 99999;
        System.out.println("x7 == y7: " + (x7==y7)); // true
        System.out.println("m7 == n7: " + (m7==n7)); // false
        System.out.println("x7.equals(y7): " + x7.equals(y7)); // true
        System.out.println("m7.equals(n7): " + m7.equals(n7)); // true
        /*
        仔细观察结果的童鞋可以发现，==比较，较小的两个相同的Integer返回true，
        较大的两个相同的Integer返回false，这是因为Integer是不变类，编译器把Integer x = 127;
        自动变为Integer x = Integer.valueOf(127);，为了节省内存，Integer.valueOf()
        对于较小的数，始终返回相同的实例，因此，==比较“恰好”为true，但我们绝不能因为Java标准库
        的Integer内部有缓存优化就用==比较，必须用equals()方法比较两个Integer。
         */
        // 按照语义编程，而不是针对特定的底层实现去“优化”。
        //因为Integer.valueOf()可能始终返回同一个Integer实例，因此，在我们自己创建Integer的时候，
        // 以下两种方法：
        //
        //方法1：Integer n = new Integer(100);
        //方法2：Integer n = Integer.valueOf(100);
        //方法2更好，因为方法1总是创建新的Integer实例，方法2把内部优化留给Integer的实现者去做，
        // 即使在当前版本没有优化，也有可能在下一个版本进行优化。
        //
        //我们把能创建“新”对象的静态方法称为静态工厂方法。Integer.valueOf()就是静态工厂方法，
        // 它尽可能地返回缓存的实例以节省内存。
        //
        // 创建新对象时，优先选用静态工厂方法而不是new操作符。
        //如果我们考察Byte.valueOf()方法的源码，可以看到，标准库返回的Byte实例全部是缓存实例，
        // 但调用者并不关心静态工厂方法以何种方式创建新实例还是直接返回缓存的实例。
    //进制转换
        //Integer类本身还提供了大量方法，例如，最常用的静态方法parseInt()可以把字符串解析成一个整数：
        int x1 = Integer.parseInt("100"); // 100
        int x2 = Integer.parseInt("100", 16); // 256,因为按16进制解析
        //Integer还可以把整数格式化为指定进制的字符串：
        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制
        //注意：上述方法的输出都是String，在计算机内存中，只用二进制表示，
        // 不存在十进制或十六进制的表示方法。int n = 100在内存中总是以4字节的二进制表示：
        /*
        ┌────────┬────────┬────────┬────────┐
        │00000000│00000000│00000000│01100100│
        └────────┴────────┴────────┴────────┘
         */
        //我们经常使用的System.out.println(n);
        // 是依靠核心库自动把整数格式化为10进制输出并显示在屏幕上，
        // 使用Integer.toHexString(n)则通过核心库自动把整数格式化为16进制。
        //
        //这里我们注意到程序设计的一个重要原则：数据的存储和显示要分离。
        //
        //Java的包装类型还定义了一些有用的静态变量
        // boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        // int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
        // long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)
        //最后，所有的整数和浮点数的包装类型都继承自Number，
        // 因此，可以非常方便地直接通过包装类型获取各种基本类型：
        // 向上转型为Number:
        Number num = new Integer(999);
        // 获取byte, int, long, float, double:
        byte b8 = num.byteValue();
        int n8 = num.intValue();
        long ln8 = num.longValue();
        float f8 = num.floatValue();
        double d8 = num.doubleValue();
    //处理无符号整型
        //在Java中，并没有无符号整型（Unsigned）的基本数据类型。
        // byte、short、int和long都是带符号整型，最高位是符号位。
        // 而C语言则提供了CPU支持的全部数据类型，包括无符号整型。
        // 无符号整型和有符号整型的转换在Java中就需要借助包装类型的静态方法完成。
        //
        //例如，byte是有符号整型，范围是-128~+127，但如果把byte看作无符号整型，
        // 它的范围就是0~255。我们把一个负的byte按无符号整型转换为int：
        byte x9 = -1;
        byte y9 = 127;
        System.out.println(Byte.toUnsignedInt(x9)); // 255
        System.out.println(Byte.toUnsignedInt(y9)); // 127
        //因为byte的-1的二进制表示是11111111，以无符号整型转换后的int就是255。
        //
        //类似的，可以把一个short按unsigned转换为int，把一个int按unsigned转换为long。
    }
}




