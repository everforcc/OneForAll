package cn.cc.dawn.business.initmsg.controller;

import cn.cc.dawn.config.init.txt.Init;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/init")
public class InitController {

    Init init =  Init.getInstanse();

    @RequestMapping("/getfun")
    public String getfun(){
        return init.getFun();
    }

}
