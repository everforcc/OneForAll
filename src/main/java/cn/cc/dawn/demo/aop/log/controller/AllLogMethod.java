package cn.cc.dawn.demo.aop.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/alllog1")
@RestController
public class AllLogMethod {

    @GetMapping("/taop1/{id}")
    public void tAOPLog1(@PathVariable("id")int id){
        System.out.println("tAOPLog1(AllLogMethod)");
    }

    @GetMapping("/taop2/{id}")
    public int tAOPLog(@PathVariable("id")int id){
        System.out.println("tAOPLog(AllLogMethod)");
        return id;
    }

}
