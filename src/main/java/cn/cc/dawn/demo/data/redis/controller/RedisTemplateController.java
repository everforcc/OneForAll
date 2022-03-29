package cn.cc.dawn.demo.data.redis.controller;

import cn.cc.dawn.demo.data.redis.service.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/open/data/redis/redisTemplateController")
@RestController
public class RedisTemplateController {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @GetMapping("/add/{k}/{v}")
    public void addKV(@PathVariable("k")String k,@PathVariable("v")String v){
        redisTemplateService.addKV(k,v);
    }

    @GetMapping("/remove/{k}")
    public void removeK(@PathVariable("k")String k){
        redisTemplateService.removeK(k);
    }

    @GetMapping("/getValue/{k}")
    public String getValue(@PathVariable("k")String k){
        return redisTemplateService.getValue(k);
    }

}
