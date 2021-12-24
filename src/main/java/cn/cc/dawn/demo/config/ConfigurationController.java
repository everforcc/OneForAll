package cn.cc.dawn.demo.config;

import cn.cc.dawn.config.yml.ConfigurationData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/config")
@RestController
public class ConfigurationController {

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
