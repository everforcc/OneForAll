package cn.cc.dawn.demo.httpclient.controller;

import cn.cc.dawn.demo.httpclient.service.HttpClientService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/httpclient")
@RestController
public class HttpClientController {

    @Autowired
    private HttpClientService httpClientService;

    @GetMapping("/feign_post/{uid}")
    public JSONObject feignClient(@PathVariable("uid")String uid){
        return httpClientService.feignPost_AlbumCount(uid);
    }

}
