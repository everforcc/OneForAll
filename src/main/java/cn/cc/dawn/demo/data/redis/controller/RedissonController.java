package cn.cc.dawn.demo.data.redis.controller;

import cn.cc.dawn.demo.data.redis.service.RedissonService;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/demo/data/redis/redissonController")
@RestController
public class RedissonController {

    @Autowired
    private RedissonService redissonService;

    /**
     * 测试分布式锁
     * [参考-但是并发数多还是有问题，已调整](https://www.cnblogs.com/niceyoo/p/13736140.html)
     */
    @GetMapping("/tLock/{id}")
    public ResultE<Boolean> tLock(@PathVariable("id")int id){
        return new ResultE<Boolean>().execute(e ->{
            e.setSuccess(redissonService.redisLock(id));
        });
    }

}
