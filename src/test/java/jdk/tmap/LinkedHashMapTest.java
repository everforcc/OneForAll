/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-22 14:46
 * Copyright
 */

package jdk.tmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");

        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();

        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

}
