package cn.cc.dawn.demo.init.map.controller;

import cn.cc.dawn.config.init.properties.PropertiesHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(value = "/demo/init/map")
@RestController
public class PropertiesController {

    @GetMapping("/map")
    public Map<String, String> str(){
        return PropertiesHeader.spring4uMap();
    }

}
