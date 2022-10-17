/**
 * @Description
 * @Author everforcc
 * @Date 2022-10-17 15:35
 * Copyright
 */

package cn.cc.dawn.utils.commons.text;

import com.alibaba.fastjson.JSON;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.util.Map;

public class StringEscapeUtilsTest {

    @Test
    public void tData() {
        String data01 = "{\"id\":\"1001\",\"name\":\"张三\",\"password\":\"zhangsan\"}";
        System.out.println("data01：" + data01);
        // 序列化为JSON字符串
        String data02 = JSON.toJSONString(data01);
        System.out.println("data02：" + data02);
        // 通过StringEscapeUtils工具类反序列化转义字符
        String data04 = StringEscapeUtils.unescapeJava(data01);
        System.out.println("data04：" + data04);
        Map map = JSON.parseObject(data04, Map.class);
        System.out.println(map.get("name"));
    }

}
