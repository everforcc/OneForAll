package cn.cc.dawn.demo.config.controller;

import cn.cc.dawn.config.init.yml.ConfigurationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/demo/config/yml")
@RestController
public class ConfigController {

    @Autowired
    private ConfigurationData configurationData;

    @GetMapping("/str")
    public String str(){
        return configurationData.getStr();
    }

    @GetMapping("/strList")
    public String strList(){
        return configurationData.getStringList().toString();
    }

}
