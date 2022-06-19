package cn.cc.dawn.demo.init.txt.controller;

import cn.cc.dawn.demo.init.txt.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/demo/init/txt")
public class InitTXTController {

    @Autowired
    InitService initService;

    @RequestMapping("/getfun")
    public String getfun(){
        return initService.init();
    }

}
