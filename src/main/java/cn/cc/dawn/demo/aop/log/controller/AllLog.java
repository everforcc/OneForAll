package cn.cc.dawn.demo.aop.log.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/alllog")
@RestController
public class AllLog {

    @GetMapping("/taop/{id}/{name}")
    public void aopLog(@PathVariable("id")int id){
        System.out.println("tAOPLog()");
        System.out.println("当前线程id:" + Thread.currentThread ().getId());
        id++;
        tAOPLog(id);
    }

    @PostMapping("/callback")
    public void callback(@RequestBody String data){
        System.out.println("data(): " + data);
    }

    public void tAOPLog(int id){
        System.out.println(id + "tAOPLog()");
    }

}
