package cn.cc.dawn.demo.init.txt.service;

import cn.cc.dawn.config.init.txt.Init;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    Init init =  Init.getInstanse();

    public String init(){
        return init.getFun();
    }

}
