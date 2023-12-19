package com.alibaba.fastjson;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author everforcc
 * @Date 2023-09-28 15:15
 * Copyright
 */
@Getter
@Setter
public class JSONObj extends JSONObject {

    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static void main(String[] args) {
        JSONObj jsonObj = new JSONObj();
        jsonObj.setName("1-name");
        System.out.println(jsonObj);
        JSONObject jsonObj1 = new JSONObject(jsonObj);
        System.out.println("2-" + jsonObj1);
        jsonObj1.put("key", "k");
        System.out.println("3-" + jsonObj1);
    }

}
