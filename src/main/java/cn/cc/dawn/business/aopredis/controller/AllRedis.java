package cn.cc.dawn.business.aopredis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/redis")
@RestController
public class AllRedis {

    @GetMapping("/taop/{id}")
    public void aopLog(@PathVariable("id")int id,@PathVariable("key")String name){
        System.out.println("tAOPLog()");
        id++;
        tAOPLog(id);
    }

    public void tAOPLog(int id){
        System.out.println(id + "tAOPLog()");
    }

}
