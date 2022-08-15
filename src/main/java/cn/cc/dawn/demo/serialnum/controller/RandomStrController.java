/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-15 17:10
 * Copyright
 */

package cn.cc.dawn.demo.serialnum.controller;

import cn.cc.dawn.demo.serialnum.service.RandomStrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RequestMapping(value = "/open/random")
@RestController
public class RandomStrController {

    @Resource
    RandomStrService randomStrService;

    @GetMapping("/str")
    public void generalStr(){
        randomStrService.generalStr();
    }

}
