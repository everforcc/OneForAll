package cn.cc.dawn.demo.data.redis.service;

import cn.cc.dawn.utils.data.redis.impl.RedisBoundValueOperationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("redisTemplateService")
public class RedisTemplateService {

    /**
     * RedisTemplate opsForValue().setIfAbsent()分布式锁的使用
     */

    @Autowired
    private RedisBoundValueOperationsUtils redisBoundValueOperationsUtils;

    public boolean addKV(String k, String v) {
        return redisBoundValueOperationsUtils.addKV(k, v);
    }

    public void removeK(String k) {
        redisBoundValueOperationsUtils.removeK(k);
    }

    public String getValue(String k) {
        return redisBoundValueOperationsUtils.getValue(k);
    }

}
