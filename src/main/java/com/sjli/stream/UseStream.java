package com.sjli.stream;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
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

        //array和connection 如何获得stream
        Stream<String> stream1 = Arrays.stream(new String[] { "A", "B", "C" });
        Stream<String> stream2 = List.of("X", "Y", "Z").stream();
        stream1.forEach(System.out::println);
        stream2.forEach(System.out::println);

        //通过supplier 生成自然数
        Stream<Integer> natual = Stream.generate(new NatualSupplier());
        // 注意：无限序列必须先变成有限序列再打印:
        natual.limit(20).forEach(System.out::println);
    }
}

class FibSupplier implements LongSupplier {
    public long getAsLong() {
        return 0;
    }

}

class NatualSupplier implements Supplier<Integer> {
    int n = 0;
    public Integer get() {
        n++;
        return n;
    }
}