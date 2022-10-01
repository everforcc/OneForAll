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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public void tjsonAry() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("a");
        jsonArray.add("b");

        System.out.println(jsonArray.toString());
        JSONObject jsonObject = new JSONObject();
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

}
