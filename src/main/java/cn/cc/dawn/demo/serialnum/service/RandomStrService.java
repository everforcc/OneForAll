/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-15 17:10
 * Copyright
 */

package cn.cc.dawn.demo.serialnum.service;

import cn.cc.dawn.demo.serialnum.util.RandomStrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class RandomStrService {

    public void generalStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ss");
        LocalTime start = LocalTime.now();
        log.info("开始生成: {}", dateTimeFormatter.format(start));
        String string = RandomStrUtil.randomString(6);
        LocalTime end = LocalTime.now();
        log.info("生成结束: {} {}", string, dateTimeFormatter.format(end));
    }

}
