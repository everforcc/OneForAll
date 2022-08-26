/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-25 16:55
 * Copyright
 */

package cn.cc.dawn.utils.commons.io;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class JIOUtilsTests {

    @Test
    void ioTests() {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("a");
        stringSet.add("b");

        Set<String> stringSet_2 = new HashSet<>();
        stringSet_2.add("a");
        stringSet_2.add("b");

        Set<String> stringSet_3 = new HashSet<>();
        stringSet_3.add("b");
        stringSet_3.add("a");

        Set<String> stringSet_4 = new HashSet<>();
        stringSet_4.add("b");
        stringSet_4.add("b");

        System.out.println(stringSet.equals(stringSet_2));
        System.out.println(stringSet.equals(stringSet_3));
        System.out.println(stringSet.equals(stringSet_4));
    }

}
