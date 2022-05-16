package cn.cc.dawn.demo.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 * aop测试
 */
@RequestMapping(value = "/d/alllog1")
@RestController
@Slf4j
public class AllLogMethod {

    @GetMapping("/taop1/{id}")
    public void tAOPLog1(@PathVariable("id")int id){
        log.info("tAOPLog1(AllLogMethod)");
    }

    @GetMapping("/taop2/{id}")
    public int tAOPLog(@PathVariable("id")int id){
        log.info("tAOPLog(AllLogMethod)");
        return id;
    }

}
