package cn.cc.dawn.local.idm.controller;

import cn.cc.dawn.local.idm.service.IDMService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/local/idm/test")
@RestController
public class IDMController {

    @Autowired
    IDMService idmService;

    @PostMapping("/down")
    public void down(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String url = jsonObject.getString("url");

        int threadSize = 3;
        if(jsonObject.containsKey("threadSize")){
            threadSize = jsonObject.getIntValue("threadSize");
        }
        idmService.flow(url,threadSize);
    }

}
