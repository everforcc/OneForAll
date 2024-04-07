/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-25 17:12
 * Copyright
 */

package jdk;

import cn.cc.dawn.utils.exception.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class TestApiTests {

    String staticStr = "staticStr";

    @Before
    public void testBefor() {
        staticStr = "before";
    }

    @Test
    public void test_1() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("a''", "a", true));
        userList.add(new User("b", "b", true));
        for (User user : userList) {
            user.setName("c");
        }

        System.out.println(userList.get(0).getName());
        Date date = new Date();
    }

    @Test
    public void tAry() {
        String[] ary = {"a", "b"};
        System.out.println(Arrays.toString(ary));
    }

    @Test
    public void tQuote() {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(2);
        Map<Integer,TTTT> ttttMap = new HashMap<>();
        TTTT tttt = new TTTT();
        tttt.setB(bigDecimal1);
        ttttMap.put(1, tttt);
        System.out.println(ttttMap.get(1));
        tttt.setB(bigDecimal2);
        System.out.println(ttttMap.get(1));
    }

    class TTTT {
        private BigDecimal b;

        public TTTT() {
        }

        public BigDecimal getB() {
            return b;
        }

        public void setB(BigDecimal b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "TTTT{" +
                    "b=" + b +
                    '}';
        }
    }

    @Test
    public void tjsonAry() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("a");
        jsonArray.add("b");

        System.out.println(jsonArray.toString());
        JSONObject jsonObject = new JSONObject();
        //Joiner.on
    }

    @Test
    public void tJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("staff_123", "aaa");
        jsonObject.put("dept_123", "aaa");

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().startsWith("staff_")) {
                jsonObject.put(entry.getKey(), "staff_bbb");
            }
        }
        jsonObject.remove("aaa");
        System.out.println(jsonObject.getString("staff_123"));

        //Joiner.on
    }

    @Test
    public void tList() {
        List<String> list = Lists.newArrayList("a", "b");
        System.out.println(list.toString());
        String result = "{" + Joiner.on(",").join(list) + "}";
        System.out.println(result);
    }

    @Test
    public void tListRecursion() {
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        System.out.println("size: " + stringList.size());
        tListRecursion(stringList, 0);
        System.out.println("size: " + stringList.size());
    }

    public void tListRecursion(List<String> stringList, int i) {
        if (i < 9) {
            i++;
            stringList.add("a");
            tListRecursion(stringList, i);
        }
    }

    @Test
    public void tBigDecimal() {
        String str = "1";
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal percent = new BigDecimal(100);
        BigDecimal result = bigDecimal.divide(percent);
        System.out.println(result.toString());
    }

}
