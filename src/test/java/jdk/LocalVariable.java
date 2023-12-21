package jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class LocalVariable {

    @Test
    public void test_1(){
        boolean type = false;
        System.out.println("1." + type);
        test_1_1(type);
        System.out.println("3." + type);
    }

    public void test_1_1(boolean type){
        type = true;
        System.out.println("2." + type);
    }

    @Test
    public void testJson(){
        String json = "{}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.toJSONString());
    }


    @Test
    public void testFormat(){
        String format = String.format("%06d", 1);
        System.out.println(format);
    }

}
