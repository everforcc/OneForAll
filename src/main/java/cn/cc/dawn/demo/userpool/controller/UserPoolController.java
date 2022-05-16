package cn.cc.dawn.demo.userpool.controller;

import cn.cc.dawn.demo.userpool.service.UserPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/d/novel/pool")
@RestController
@Slf4j
public class UserPoolController {

    @Autowired
    UserPoolService userPoolService;

    @GetMapping("/testPool")
    public void testPool(){
        userPoolService.testPool();
    }

    @GetMapping("/testPool2")
    public void testPool2(){
        userPoolService.testPool2();
    }

}
