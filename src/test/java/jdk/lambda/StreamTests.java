/**
 * @Description
 * @Author everforcc
 * @Date 2023-03-31 14:28
 * Copyright
 */

package jdk.lambda;

import jdk.lang.AutoCloseableDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StreamTests {

    public static void main(String[] args) {
        streamFilter();
    }

    public static void streamFilter() {
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
