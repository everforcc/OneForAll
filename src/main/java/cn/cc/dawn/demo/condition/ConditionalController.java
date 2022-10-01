/**
 * @Description
 * @Author everforcc
 * @Date 2022-09-30 10:17
 * Copyright
 */

package cn.cc.dawn.demo.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open/condition")
@ConditionalOnExpression("'local'.equals('${spring.app.env}')")
public class ConditionalController {

    @GetMapping("/m1")
    public String selectForeach() {
        return "m1";
    }

}