package com.sjli.stream;

import java.io.IOException;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Classname Stream
 * @Description 惰性计算
 * @Date 2021/9/10 11:11
 * @Created by steven
 */
public class UseStream<B extends Number> {
    public static void main(String[] args) throws IOException {
        Stream<String> stream = Stream.of("A", "B", "C", "D");
        // forEach()方法相当于内部循环调用，
        // 可传入符合Consumer接口的void accept(T t)的方法引用：
        stream.forEach(System.out::println);

        LongStream fib = LongStream.generate(new FibSupplier());
        // 打印Fibonacci数列：1，1，2，3，5，8，13，21...
        fib.limit(10).forEach(System.out::println);
    }
}

class FibSupplier implements LongSupplier {
    public long getAsLong() {
        return 0;
    }

}
