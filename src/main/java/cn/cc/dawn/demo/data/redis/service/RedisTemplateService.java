package cn.cc.dawn.demo.data.redis.service;

import cn.cc.dawn.utils.data.redis.impl.RedisBoundValueOperationsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("redisTemplateService")
public class RedisTemplateService {

    /**
     * RedisTemplate opsForValue().setIfAbsent()分布式锁的使用
     */

    @Autowired
    private RedisBoundValueOperationsUtils redisBoundValueOperationsUtils;

    public boolean addKV(String k, String v) {
        boolean flag = redisBoundValueOperationsUtils.addKV(k, v);
        log.debug("flag: {}", flag);
        return flag;
    }

    public void removeK(String k) {
        redisBoundValueOperationsUtils.removeK(k);
    }

    public String getValue(String k) {
        String value = redisBoundValueOperationsUtils.getValue(k);
        log.debug("value: {}", value);
        return value;
    }

}
