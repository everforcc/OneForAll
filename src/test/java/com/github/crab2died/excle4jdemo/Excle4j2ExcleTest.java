/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-21 11:53
 * Copyright
 */

package com.github.crab2died.excle4jdemo;

import com.alibaba.fastjson.JSONObject;
import com.github.crab2died.ExcelUtils;
import com.github.crab2died.excle4jdemo.dto.Student1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 写入excel
 */
public class Excle4j2ExcleTest {

    /**
     * 1. 从对象 写入excel
     * @throws Exception
     */
    @Test
    public void testObject2Excel() throws Exception {
        //String tempPath = "/normal_template.xlsx";
        List<Student1> list = new ArrayList<>();
        list.add(new Student1("1010001", "盖伦", "六年级三班"));
        list.add(new Student1("1010002", "古尔丹", "一年级三班"));
        list.add(new Student1("1010003", "蒙多(被开除了)", "六年级一班"));
        list.add(new Student1("1010004", "萝卜特", "三年级二班"));
        list.add(new Student1("1010005", "奥拉基", "三年级二班"));
        list.add(new Student1("1010006", "得嘞", "四年级二班"));
        list.add(new Student1("1010007", "瓜娃子", "五年级一班"));
        list.add(new Student1("1010008", "战三", "二年级一班"));
        list.add(new Student1("1010009", "李四", "一年级一班"));
        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");
        // 基于模板导出Excel
        //ExcelUtils.getInstance().exportObjects2Excel(tempPath, 0, list, data, Student1.class, false, "./A.xlsx");
        // 不基于模板导出Excel
        ExcelUtils.getInstance().exportObjects2Excel(list, Student1.class, true, null, true, "./A.xlsx");

    }

    /**
     * 2. 从 map 写入excel
     * @throws Exception
     */
    @Test
    public void testMap2Excel() throws Exception {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("K1", "V1");
        list.add(map);
        map = new HashMap<>();
        map.put("K1", "V2");
        list.add(map);
        map = new HashMap<>();
        map.put("K1", "V3");
        list.add(map);

        String targetPath = "./C.xlsx";
        ExcelUtils.getInstance().exportObjects2Excel(list, targetPath);
    }

    /**
     * 3. list/list 纯自定义导出excel
     * @throws Exception 异常
     */
    @Test
    public void testList2Excel() throws Exception {
        List<List<String>> list = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        stringList.add("标题-1");
        stringList.add("标题-2");
        stringList.add("标题-3");
        list.add(stringList);

        stringList = new ArrayList<>();
        stringList.add("v-1");
        stringList.add("v-2");
        stringList.add("v-3");
        list.add(stringList);

        String targetPath = "./C.xlsx";
        ExcelUtils.getInstance().exportObjects2Excel(list, targetPath);
    }

    /**
     * 4. 从 json 写入excel
     * @throws Exception
     */
    @Test
    public void testJSON2Excel() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key_1", "value");
        jsonObject.put("key_2", "value");
        jsonObject.put("key_3", "value");
        jsonObject.put("key_4", "value");
        Map<String, String> map = jsonObject.toJavaObject(Map.class);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        jsonObjectList.add(jsonObject);
        String targetPath = "./C.xlsx";
        ExcelUtils.getInstance().exportObjects2Excel(jsonObjectList, targetPath);
    }


}
