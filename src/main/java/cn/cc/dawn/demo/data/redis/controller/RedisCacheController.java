package cn.cc.dawn.demo.data.redis.controller;

import cn.cc.dawn.demo.data.redis.service.RedisCacheService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author everforcc 2021-09-25
 */
@RequestMapping(value = "/demo/data/redis/redisCacheController")
@RestController
@Slf4j
public class RedisCacheController {

    @Autowired
    RedisCacheService redisCacheService;

    @GetMapping("/redisCacheable/{str}")
    public ResultE<String> redisCacheable(@PathVariable("str")String str){
        log.info("redisCacheable:" + str);
        return new ResultE<String>().execute(e ->{
            e.setSuccess(redisCacheService.redisCacheable(str));
        });
    }

    @GetMapping("/redisCacheableCondition/{str}")
    public ResultE<String> redisCacheableCondition(@PathVariable("str")String str){
        log.info("redisCacheableCondition:" + str);
        return new ResultE<String>().execute(e ->{
            e.setSuccess(redisCacheService.redisCacheableCondition(str));
        });
    }

    @GetMapping("/redisCachePut/{str}")
    public ResultE<String> redisCachePut(@PathVariable("str")String str){
        log.info("redisCachePut:" + str);
        return new ResultE<String>().execute(e ->{
            e.setSuccess(redisCacheService.redisCachePut(str));
        });
    }

    @GetMapping("/redisCacheEvict/{str}")
    public ResultE<String> redisCacheEvict(@PathVariable("str")String str){
        log.info("redisCacheEvict:" + str);
        return new ResultE<String>().execute(e ->{
            e.setSuccess(redisCacheService.redisCacheEvict(str));
        });
    }


}
