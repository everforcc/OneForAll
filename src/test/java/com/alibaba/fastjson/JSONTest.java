/**
 * @Description
 * @Author everforcc
 * @Date 2022-12-22 15:08
 * Copyright
 */

package com.alibaba.fastjson;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JSONTest {

    @Test
    public void test_toJSONString() {
        String a = "111";
        String aJson = JSON.toJSONString(a);
        log.info("aJson: {}", aJson);
        String aa = JSON.parseObject(aJson, String.class);
        log.info("aa: {}", aa);
        log.info("a: {}", a);
        long aValue = Long.valueOf(a);
        log.info("aValue: {}", aValue);
        long longA = Long.parseLong(a);
        log.info("longA: {}", longA);
    }

}
