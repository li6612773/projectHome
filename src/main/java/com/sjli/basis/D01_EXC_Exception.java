package com.sjli.basis;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class D01_EXC_Exception {
    public static void main(String[] args) {
    //Java的异常
        //在计算机程序运行的过程中，总是会出现各种各样的错误。
        //
        //有一些错误是用户造成的，比如，希望用户输入一个int类型的年龄，但是用户的输入是abc：
        // 假设用户输入了abc：
        String s = "abc";
        //int n = Integer.parseInt(s); // NumberFormatException!
        //程序想要读写某个文件的内容，但是用户已经把它删除了：
        // 用户删除了该文件：
        //tring t = readFile("C:\\abc.txt"); // FileNotFoundException!
        //还有一些错误是随机出现，并且永远不可能避免的。比如：
        //
        //网络突然断了，连接不到远程服务器；
        //内存耗尽，程序崩溃了；
        //用户点“打印”，但根本没有打印机；
        //……
        //所以，一个健壮的程序必须处理各种各样的错误。
        //
        //所谓错误，就是程序调用某个函数的时候，如果失败了，就表示出错。
        //
        //调用方如何获知调用失败的信息？有两种方法：
        //
        //方法一：约定返回错误码。
        //
        //例如，处理一个文件，如果返回0，表示成功，返回其他整数，表示约定的错误码：
        /*
        int code = processFile("C:\\test.txt");
        if (code == 0) {
            // ok:
        } else {
            // error:
            switch (code) {
                case 1:
                    // file not found:
                case 2:
                    // no read permission:
                default:
                    // unknown error:
            }
        }
        */
        //因为使用int类型的错误码，想要处理就非常麻烦。这种方式常见于底层C函数。
        //
        //方法二：在语言层面上提供一个异常处理机制。
        //
        //Java内置了一套异常处理机制，总是使用异常来表示错误。
        //
        //异常是一种class，因此它本身带有类型信息。异常可以在任何地方抛出，
        // 但只需要在上层捕获，这样就和方法调用分离了：
        /*
        try {
            String s = processFile(“C:\\test.txt”);
            // ok:
        } catch (FileNotFoundException e) {
            // file not found:
        } catch (SecurityException e) {
            // no read permission:
        } catch (IOException e) {
            // io error:
        } catch (Exception e) {
            // other error:
        }
        因为Java的异常是class，它的继承关系如下：

                             ┌───────────┐
                             │  Object   │
                             └───────────┘
                                   ▲
                                   │
                             ┌───────────┐
                             │ Throwable │
                             └───────────┘
                                   ▲
                         ┌─────────┴─────────┐
                         │                   │
                   ┌───────────┐       ┌───────────┐
                   │   Error   │       │ Exception │
                   └───────────┘       └───────────┘
                         ▲                   ▲
                 ┌───────┘              ┌────┴──────────┐
                 │                      │               │
        ┌─────────────────┐    ┌─────────────────┐┌───────────┐
        │OutOfMemoryError │... │RuntimeException ││IOException│...
        └─────────────────┘    └─────────────────┘└───────────┘
                                        ▲
                            ┌───────────┴─────────────┐
                            │                         │
                 ┌─────────────────────┐ ┌─────────────────────────┐
                 │NullPointerException │ │IllegalArgumentException │...
                 └─────────────────────┘ └─────────────────────────┘

        从继承关系可知：Throwable是异常体系的根，它继承自Object。
        Throwable有两个体系：Error和Exception，Error表示严重的错误，程序对此一般无能为力，例如：

        OutOfMemoryError：内存耗尽
        NoClassDefFoundError：无法加载某个Class
        StackOverflowError：栈溢出
        而Exception则是运行时的错误，它可以被捕获并处理。

        某些异常是应用程序逻辑处理的一部分，应该捕获并处理。例如：

        NumberFormatException：数值类型的格式错误
        FileNotFoundException：未找到文件
        SocketException：读取网络失败
        还有一些异常是程序逻辑编写不对造成的，应该修复程序本身。例如：

        NullPointerException：对某个null的对象调用方法或字段
        IndexOutOfBoundsException：数组索引越界
        Exception又分为两大类：

        RuntimeException以及它的子类；
        非RuntimeException（包括IOException、ReflectiveOperationException等等）
        */

        //Java规定：
        //
        //必须捕获的异常，包括Exception及其子类，但不包括RuntimeException及其子类，
        // 这种类型的异常称为Checked Exception。
        //
        //不需要捕获的异常，包括Error及其子类，RuntimeException及其子类。
        //
        // 注意：编译器对RuntimeException及其子类不做强制捕获要求，
        // 不是指应用程序本身不应该捕获并处理RuntimeException。是否需要捕获，具体问题具体分析。
    //捕获异常
        //捕获异常使用try...catch语句，把可能发生异常的代码放到try {...}中，
        // 然后使用catch捕获对应的Exception及其子类：
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));
        //如果我们不捕获UnsupportedEncodingException，会出现编译失败的问题：
        //编译器会报错，错误信息类似：unreported exception UnsupportedEncodingException;
        // ust be caught or declared to be thrown，并且准确地指出需要捕获的语句是
        // return s.getBytes("GBK");。意思是说，像UnsupportedEncodingException这样的
        // Checked Exception，必须被捕获。
        //
        //这是因为String.getBytes(String)方法定义是：
        //
        //public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
        //    ...
        //}
        //在方法定义的时候，使用throws Xxx表示该方法可能抛出的异常类型。调用方在调用的时候，
        // 必须强制捕获这些异常，否则编译器会报错。
        //
        //在toGBK()方法中，因为调用了String.getBytes(String)方法，
        // 就必须捕获UnsupportedEncodingException。我们也可以不捕获它，
        // 而是在方法定义处用throws表示toGBK()方法可能会抛出UnsupportedEncodingException，
        // 就可以让toGBK()方法通过编译器检查：
        //static byte[] toGBK(String s) throws UnsupportedEncodingException {
        //        return s.getBytes("GBK");

        //上述代码仍然会得到编译错误，但这一次，编译器提示的不是调用return s.getBytes("GBK");
        // 的问题，而是byte[] bs = toGBK("中文");。因为在main()方法中，调用toGBK()，
        // 没有捕获它声明的可能抛出的UnsupportedEncodingException。
        //
        //修复方法是在main()方法中捕获异常并处理：
        /*
        public static void main(String[] args) {
            try {
                byte[] bs = toGBK("中文");
                System.out.println(Arrays.toString(bs));
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            }
        }
         */
        //可见，只要是方法声明的Checked Exception，不在调用层捕获，也必须在更高的调用层捕获。
        // 所有未捕获的异常，最终也必须在main()方法中捕获，不会出现漏写try的情况。
        // 这是由编译器保证的。main()方法也是最后捕获Exception的机会。
        //
        //如果是测试代码，上面的写法就略显麻烦。如果不想写任何try代码，可以直接把main()方法定义为throws Exception：
        /*
        public static void main(String[] args) throws Exception {
            byte[] bs = toGBK("中文");
            System.out.println(Arrays.toString(bs));
        }
         */
        //因为main()方法声明了可能抛出Exception，也就声明了可能抛出所有的Exception，因此在内部就无需捕获了。
        // 代价就是一旦发生异常，程序会立刻退出。
        //
        //还有一些童鞋喜欢在toGBK()内部“消化”异常：
        /*
        static byte[] toGBK(String s) {
            try {
                return s.getBytes("GBK");
            } catch (UnsupportedEncodingException e) {
                // 什么也不干
            }
            return null;
         */
        //这种捕获后不处理的方式是非常不好的，即使真的什么也做不了，也要先把异常记录下来：
        /*
        static byte[] toGBK(String s) {
            try {
                return s.getBytes("GBK");
            } catch (UnsupportedEncodingException e) {
                // 先记下来再说:
                e.printStackTrace();
            }
            return null;
         */
        //所有异常都可以调用printStackTrace()方法打印异常栈，这是一个简单有用的快速打印异常的方法。

    }

    static byte[] toGBK(String s2) {
        try {
            // 用指定编码转换String为byte[]:
            return s2.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            // 如果系统不支持GBK编码，会捕获到UnsupportedEncodingException:
            System.out.println(e); // 打印异常信息
            return s2.getBytes(); // 尝试使用用默认编码
        }
    }
}
