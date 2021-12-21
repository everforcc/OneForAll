package cn.cc.dawn.business.cacheredis.controller;

import cn.cc.dawn.business.cacheredis.service.RedisBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/redis")
@RestController
public class RedisController {

    @Autowired
    RedisBusiness redisBusiness;

    @GetMapping("/redisCacheable/{str}")
    public String redisCacheable(@PathVariable("str")String str){
        System.out.println("redisCacheable:" + str);
        return redisBusiness.redisCacheable(str);
    }

    @GetMapping("/redisCacheableCondition/{str}")
    public String redisCacheableCondition(@PathVariable("str")String str){
        System.out.println("redisCacheableCondition:" + str);
        return redisBusiness.redisCacheableCondition(str);
    }

    @GetMapping("/redisCachePut/{str}")
    public String redisCachePut(@PathVariable("str")String str){
        System.out.println("redisCachePut:" + str);
        return redisBusiness.redisCachePut(str);
    }

    @GetMapping("/redisCacheEvict/{str}")
    public String redisCacheEvict(@PathVariable("str")String str){
        System.out.println("redisCacheEvict:" + str);
        return redisBusiness.redisCacheEvict(str);
    }


}
