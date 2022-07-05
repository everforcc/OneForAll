/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-04 10:42
 * Copyright
 */

package cn.cc.dawn.open.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 因为初始化的时候这个类就一分所以每次进来,都会在之前的基础上+1
 */
@Slf4j
@RequestMapping("/open/memo")
@RestController
public class MemoryController {

    private int a = 1;

    @GetMapping("/a")
    public int getA() {
        a++;
        log.info("a: {}", a);
        return a;
    }

}
