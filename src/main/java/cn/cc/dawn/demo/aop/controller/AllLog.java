package cn.cc.dawn.demo.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author everforcc 2021-09-25
 */
@Slf4j
@RequestMapping(value = "/d/alllog")
@RestController
public class AllLog {

    @GetMapping("/taop/{id}/{name}")
    public void aopLog(@PathVariable("id")int id){
        log.info("tAOPLog()");
        log.info("当前线程id:" + Thread.currentThread ().getId());
        id++;
        tAOPLog(id);
    }

    @PostMapping("/callback")
    public void callback(@RequestBody String data){
        log.info("data(): " + data);
    }

    public void tAOPLog(int id){
        log.info(id + "tAOPLog()");
    }

}
