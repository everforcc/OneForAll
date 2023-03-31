/**
 * @Description
 * @Author everforcc
 * @Date 2023-03-31 14:28
 * Copyright
 */

package jdk.lambda;

import jdk.lang.AutoCloseableDto;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTests {

    /**
     * 1. 生成
     */
    @Test
    public void streamGenerate() {
        // 1. 生成流
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    /**
     * 2. forEach
     */
    @Test
    public void streamForEach() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * 3. map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
     */
    @Test
    public void streamMap() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        //List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        numbers.stream().map(i -> i * i).distinct().forEach(System.out::println);
        System.out.println("------");
        numbers.stream().distinct().map(i -> i * i).forEach(System.out::println);
    }

    /**
     * 4. filter
     */
    @Test
    public void streamFilter() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(String::isEmpty).limit(1).count();
        System.out.println("count: " + count);
    }

    /**
     * 5. limit
     */
    @Test
    public void streamLimit() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * 6. sorted
     */
    @Test
    public void streamSorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        System.out.println("------");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        List<Integer> squaresList = numbers.stream()
                .map(i -> i * i)
                .sorted((x, y) -> y - x)
                .collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    /**
     * 7. Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     */
    @Test
    public void streamCollectors() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    /**
     * 8. 另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
     */
    @Test
    public void streamStatistics() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    @Test
    public void streamParallelStream() {
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerListSync = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integerList.add(i);
            integerListSync.add(i);
        }

        List<Integer> list = new ArrayList<>();
        List<Integer> listSync = Collections.synchronizedList(new ArrayList<>());

        integerList.parallelStream().forEach(list::add);
        integerListSync.parallelStream().forEach(listSync::add);

        System.out.println("list.size(): " + list.size());
        System.out.println("线程安全的对象listSync.size(): " + listSync.size());
    }


    @Test
    public void streamTTT() {
        Set<AutoCloseableDto> autoCloseableDtoHashSet = new HashSet<>();
        autoCloseableDtoHashSet.add(new AutoCloseableDto("aaa"));
        autoCloseableDtoHashSet.add(new AutoCloseableDto("ccc"));
        autoCloseableDtoHashSet.add(new AutoCloseableDto("ddd"));

        List<AutoCloseableDto> autoCloseableDtoArrayList = new ArrayList<>();
        autoCloseableDtoArrayList.add(new AutoCloseableDto("aaa"));
        autoCloseableDtoArrayList.add(new AutoCloseableDto("bbb"));
        autoCloseableDtoArrayList.add(new AutoCloseableDto("aaa"));
        autoCloseableDtoArrayList.add(new AutoCloseableDto("ccc"));

        System.out.println(autoCloseableDtoHashSet.size());

//        autoCloseableDtoHashSet.parallelStream()
//                .filter(autoCloseableDtoArrayList::contains)
//                .forEach(autoCloseableDtoHashSet::remove);

        autoCloseableDtoArrayList.parallelStream()
                .filter(autoCloseableDtoHashSet::contains)
                //       .forEach(System.out::println)
                .forEach(autoCloseableDtoHashSet::remove)
        ;

        autoCloseableDtoHashSet.parallelStream()
                .forEach(System.out::println);

        System.out.println(autoCloseableDtoHashSet.size());
    }

}
