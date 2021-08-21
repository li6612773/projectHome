package com.sjli.collection;

/**
 * @Classname ListUse
 * @Description TODO
 * @Date 2021/8/21 15:33
 * @Created by steven
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 在集合类中，List是最基础的一种集合：它是一种有序列表。
 *
 * List的行为和数组几乎完全相同：List内部按照放入元素的先后顺序存放，
 * 每个元素都可以通过索引确定自己的位置，List的索引和数组一样，从0开始。
 *
 * 数组和List类似，也是有序结构，如果我们使用数组，在添加和删除元素的时候，会非常不方便。
 * 例如，从一个已有的数组{'A', 'B', 'C', 'D', 'E'}中删除索引为2的元素：
 *
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ C │ D │ E │   │
 * └───┴───┴───┴───┴───┴───┘
 *               │   │
 *           ┌───┘   │
 *           │   ┌───┘
 *           │   │
 *           ▼   ▼
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ D │ E │   │   │
 * └───┴───┴───┴───┴───┴───┘
 * 这个“删除”操作实际上是把'C'后面的元素依次往前挪一个位置，而“添加”操作实际上是把指定位置
 * 以后的元素都依次向后挪一个位置，腾出来的位置给新加的元素。这两种操作，用数组实现非常麻烦。
 *
 * 因此，在实际应用中，需要增删元素的有序列表，我们使用最多的是ArrayList。实际上，
 * ArrayList在内部使用了数组来存储所有元素。例如，一个ArrayList拥有5个元素，
 * 实际数组大小为6（即有一个空位）：
 *
 * size=5
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ C │ D │ E │   │
 * └───┴───┴───┴───┴───┴───┘
 * 当添加一个元素并指定索引到ArrayList时，ArrayList自动移动需要移动的元素：
 *
 * size=5
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │   │ C │ D │ E │
 * └───┴───┴───┴───┴───┴───┘
 * 然后，往内部指定索引的数组位置添加一个元素，然后把size加1：
 *
 * size=6
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ F │ C │ D │ E │
 * └───┴───┴───┴───┴───┴───┘
 * 继续添加元素，但是数组已满，没有空闲位置的时候，ArrayList先创建一个更大的新数组，
 * 然后把旧数组的所有元素复制到新数组，紧接着用新数组取代旧数组：
 *
 * size=6
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
 * │ A │ B │ F │ C │ D │ E │   │   │   │   │   │   │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
 * 现在，新数组就有了空位，可以继续添加一个元素到数组末尾，同时size加1：
 *
 * size=7
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
 * │ A │ B │ F │ C │ D │ E │ G │   │   │   │   │   │
 * └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
 * 可见，ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。
 *
 * 我们考察List<E>接口，可以看到几个主要的接口方法：
 *
 * 在末尾添加一个元素：boolean add(E e)
 * 在指定索引添加一个元素：boolean add(int index, E e)
 * 删除指定索引的元素：E remove(int index)
 * 删除某个元素：boolean remove(Object e)
 * 获取指定索引的元素：E get(int index)
 * 获取链表大小（包含元素的个数）：int size()
 * 但是，实现List接口并非只能通过数组（即ArrayList的实现方式）来实现，
 * 另一种LinkedList通过“链表”也实现了List接口。在LinkedList中，它的内部每个元素都指向下一个元素：
 *
 *         ┌───┬───┐   ┌───┬───┐   ┌───┬───┐   ┌───┬───┐
 * HEAD ──>│ A │ ●─┼──>│ B │ ●─┼──>│ C │ ●─┼──>│ D │   │
 *         └───┴───┘   └───┴───┘   └───┴───┘   └───┴───┘
 * 我们来比较一下ArrayList和LinkedList：
 *
 *                   ArrayList	    LinkedList
 * 获取指定元素	     速度很快	    需要从头开始查找元素
 * 添加元素到末尾	     速度很快	    速度很快
 * 在指定位置添加/删除	 需要移动元素	    不需要移动元素
 * 内存占用	         少	            较大
 *
 * 通常情况下，我们总是优先使用ArrayList。
 */


public class List_In_Java {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int n=0;n<100;n++){
            list.add("apple"+n);
        }
        list.add("apple");
        list.add("apple");//允许重复添加元素
        list.add(null);//允许添加null
        System.out.println(list.toString());

        System.out.println(list.get(102)); //访问一个元素
        //但这种方式并不推荐，一是代码复杂，二是因为get(int)方法只有ArrayList的实现是高效的，
        // 换成LinkedList后，索引越大，访问速度越慢。

        //所以我们要始终坚持使用迭代器Iterator来访问List。Iterator本身也是一个对象，
        // 但它是由List的实例调用iterator()方法的时候创建的。Iterator对象知道如何遍历一个List，
        // 并且不同的List类型，返回的Iterator对象实现也是不同的，但总是具有最高的访问效率。
        //
        //Iterator对象有两个方法：boolean hasNext()判断是否有下一个元素，E next()返回下一个元素。
        // 因此，使用Iterator遍历List代码如下：
        List<String> list2 = List.of("apple", "pear", "banana");
        for (Iterator<String> it = list2.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }
        //有童鞋可能觉得使用Iterator访问List的代码比使用索引更复杂。
        // 但是，要记住，通过Iterator遍历List永远是最高效的方式。
        // 并且，由于Iterator遍历是如此常用，所以，Java的for each循环本身就可以帮我们使用Iterator遍历。
        // 把上面的代码再改写如下：
        for (String s : list2) {
            System.out.println(s);
        }

        //List和Array转换
        //把List变为Array有三种方法，第一种是调用toArray()方法直接返回一个Object[]数组：
        System.out.println("List和Array转换,方法1：");
        Object[] array = list2.toArray();
        for (Object s : array) {
            System.out.println(s);
        }

        //这种方法会丢失类型信息，所以实际应用很少。
        //
        //第二种方式是给toArray(T[])传入一个类型相同的Array，List内部自动把元素复制到传入的Array中：
        System.out.println("List和Array转换,方法2：");
        List<Integer> list3 = List.of(12, 34, 56);
        Integer[] array3 = list3.toArray(new Integer[3]);
        for (Integer n : array3) {
            System.out.println(n);
        }
        //也可以传入number
        Number[] array4 = list3.toArray(new Number[list2.size()]);
        for (Number n : array4) {
            System.out.println(n);
        }

        //最后一种更简洁的写法是通过List接口定义的T[] toArray(IntFunction<T[]> generator)方法：
        System.out.println("List和Array转换,方法3：");
        Integer[] array5 = list3.toArray(Integer[]::new);

        //反过来，把Array变为List就简单多了，通过List.of(T...)方法最简单：
        System.out.println("Array转换为List");
        Integer[] array6 = { 1, 2, 3 };
        List<Integer> list6 = List.of(array6);
        //要注意的是，返回的List不一定就是ArrayList或者LinkedList，因为List只是一个接口，如果我们调用List.of()，它返回的是一个只读List：
        List<Integer> list7 = List.of(12, 34, 56);
//        list7.add(999); // UnsupportedOperationException 对只读List调用add()、remove()方法会抛出UnsupportedOperationException。

        //我们知道List是一种有序链表：List内部按照放入元素的先后顺序存放，并且每个元素都可以通过索引确定自己的位置。
        //
        //List还提供了boolean contains(Object o)方法来判断List是否包含某个指定元素。
        // 此外，int indexOf(Object o)方法可以返回某个元素的索引，如果元素不存在，就返回-1。
        System.out.println("contains和indexof：");
        List<String> list8 = List.of("A", "B", "C", "C");
        System.out.println(list8.contains("C")); // true
        System.out.println(list8.contains("X")); // false
        System.out.println(list8.indexOf("C")); // 2
        System.out.println(list8.indexOf("X")); // -1

        //为使自定义的对象，放到list里面以后，可以使用contains方法找到，必须实现该对象的equals方法
        System.out.println("编写equals方法：");
        int age = 0;
        List<Person> list9 = List.of(
                new Person("Xiao Ming", age),
                new Person("Xiao Hong", age),
                new Person("Bob", age)
        );
        System.out.println(list9.contains(new Person("Bob", age))); // false

    }
}
