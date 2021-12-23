package cn.cc.dawn.demo.data.redis.controller;

import cn.cc.dawn.demo.data.redis.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/redisCache")
@RestController
public class RedisCacheController {

    @Autowired
    RedisCacheService redisCacheService;

    @GetMapping("/redisCacheable/{str}")
    public String redisCacheable(@PathVariable("str")String str){
        System.out.println("redisCacheable:" + str);
        return redisCacheService.redisCacheable(str);
    }

    @GetMapping("/redisCacheableCondition/{str}")
    public String redisCacheableCondition(@PathVariable("str")String str){
        System.out.println("redisCacheableCondition:" + str);
        return redisCacheService.redisCacheableCondition(str);
    }

    @GetMapping("/redisCachePut/{str}")
    public String redisCachePut(@PathVariable("str")String str){
        System.out.println("redisCachePut:" + str);
        return redisCacheService.redisCachePut(str);
    }

    @GetMapping("/redisCacheEvict/{str}")
    public String redisCacheEvict(@PathVariable("str")String str){
        System.out.println("redisCacheEvict:" + str);
        return redisCacheService.redisCacheEvict(str);
    }


}
