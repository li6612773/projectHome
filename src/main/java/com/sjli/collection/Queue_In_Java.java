package com.sjli.collection;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列（Queue）是一种经常使用的集合。Queue实际上是实现了一个先进先出（FIFO：First In First Out）的有序表。
 * 它和List的区别在于，List可以在任意位置添加和删除元素，而Queue只有两个操作：
 *
 * 把元素添加到队列末尾；
 * 从队列头部取出元素。
 * 超市的收银台就是一个队列：
 *
 * queue
 *
 * 在Java的标准库中，队列接口Queue定义了以下几个方法：
 *
 * int size()：获取队列长度；
 * boolean add(E)/boolean offer(E)：添加元素到队尾；
 * E remove()/E poll()：获取队首元素并从队列中删除；
 * E element()/E peek()：获取队首元素但并不从队列中删除。
 * 对于具体的实现类，有的Queue有最大队列长度限制，有的Queue没有。注意到添加、删除和获取队列元素总是有两个方法，
 * 这是因为在添加或获取元素失败时，这两个方法的行为是不同的。我们用一个表格总结如下：
 *
 * throw Exception	返回false或null
 * 添加元素到队尾	add(E e)	boolean offer(E e)
 * 取队首元素并删除	E remove()	E poll()
 * 取队首元素但不删除	E element()	E peek()
 * 举个栗子，假设我们有一个队列，对它做一个添加操作，如果调用add()方法，当添加失败时（可能超过了队列的容量），它会抛出异常：
 *
 * Queue<String> q = ...
 * try {
 *     q.add("Apple");
 *     System.out.println("添加成功");
 * } catch(IllegalStateException e) {
 *     System.out.println("添加失败");
 * }
 * 如果我们调用offer()方法来添加元素，当添加失败时，它不会抛异常，而是返回false：
 *
 * Queue<String> q = ...
 * if (q.offer("Apple")) {
 *     System.out.println("添加成功");
 * } else {
 *     System.out.println("添加失败");
 * }
 * 当我们需要从Queue中取出队首元素时，如果当前Queue是一个空队列，调用remove()方法，它会抛出异常：
 *
 * Queue<String> q = ...
 * try {
 *     String s = q.remove();
 *     System.out.println("获取成功");
 * } catch(IllegalStateException e) {
 *     System.out.println("获取失败");
 * }
 * 如果我们调用poll()方法来取出队首元素，当获取失败时，它不会抛异常，而是返回null：
 *
 * Queue<String> q = ...
 * String s = q.poll();
 * if (s != null) {
 *     System.out.println("获取成功");
 * } else {
 *     System.out.println("获取失败");
 * }
 * 因此，两套方法可以根据需要来选择使用。
 *
 * 注意：不要把null添加到队列中，否则poll()方法返回null时，很难确定是取到了null元素还是队列为空。
 *
 * 接下来我们以poll()和peek()为例来说说“获取并删除”与“获取但不删除”的区别。对于Queue来说，
 * 每次调用poll()，都会获取队首元素，并且获取到的元素已经从队列中被删除了：
 */

public class Queue_In_Java {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        // 从队列取出元素:
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // null,因为队列是空的

        //用peek 取出不删除，可反复取
        Queue<String> q2 = new LinkedList<>();
        // 添加3个元素到队列:
        q2.offer("apple");
        q2.offer("pear");
        q2.offer("banana");
        // 队首永远都是apple，因为peek()不会删除它:
        System.out.println(q2.peek()); // apple
        System.out.println(q2.peek()); // apple
        System.out.println(q2.peek()); // apple

        //从上面的代码中，我们还可以发现，LinkedList即实现了List接口，
        // 又实现了Queue接口，但是，在使用的时候，如果我们把它当作List，
        // 就获取List的引用，如果我们把它当作Queue，就获取Queue的引用：
    }
}
