package cn.cc.dawn.demo.ngbalance.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-28
 */
@RestController
@RequestMapping("/d/ng")
public class NginxBalanceController {

    @Value("${server.port:}")
    private String port;

    @GetMapping("/nginx1")
    public String balance1(){
        return port+":nginx1";
    }

    @GetMapping("/nginx2")
    public String balance2(){
        return port+":nginx2";
    }

}
