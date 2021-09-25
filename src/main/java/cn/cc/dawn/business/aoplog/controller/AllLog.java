package cn.cc.dawn.business.aoplog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guokailong 2021-09-25
 */
@RequestMapping(value = "/alllog")
@RestController
public class AllLog {

    @GetMapping("/taop/{id}")
    public void tAOPLog(@PathVariable("id")int id){
        System.out.println("tAOPLog()");
    }

}
