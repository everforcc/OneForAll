package cn.cc.dawn.utils.data.redis.impl;

import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.data.redis.IRedisTemplate;
import cn.cc.dawn.utils.enums.BooleanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisBoundValueOperationsUtils implements IRedisTemplate {

    /**
     * 直接看文档和示例用就好了
     */

    String cacheKeyFormat = "%s::%s";

    /**
     * 其他方法看文档
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // inject the template as ListOperations
    //@Resource(name="redisTemplate")
    //private ListOperations<String, String> listOps;

    /*public void addKV(String k, String v) {
        listOps.leftPush(k, v);
        //BoundValueOperations<String, String> strRedis = template.boundValueOps("key");
    }*/

    public boolean addKV(String k, String v) {
        BoundValueOperations<String, String> strRedis = redisTemplate.boundValueOps(k);
        //判断key是否有对应的value,如果有,则返回false,如果没有,添加,返回true
        return RObjectsUtils.equals(strRedis.setIfAbsent(v, Duration.ofSeconds(60)), BooleanEnum.TRUE.flag);
    }

    @Override
    public boolean addKV(String k, String v,Duration timeout) {
        BoundValueOperations<String, String> strRedis = redisTemplate.boundValueOps(k);
        //判断key是否有对应的value,如果有,则返回false,如果没有,添加,返回true
        return RObjectsUtils.equals(strRedis.setIfAbsent(v, timeout), BooleanEnum.TRUE.flag);
    }

    public void removeK(String k) {
        BoundValueOperations<String, String> strRedis = redisTemplate.boundValueOps(k);
        strRedis.persist();
    }

    @Override
    public String getValue(String k) {
        BoundValueOperations<String, String> strRedis = redisTemplate.boundValueOps(k);

        return strRedis.get();
    }

    public String getValueCacheAble(String n,String k) {
        BoundValueOperations<String, String> strRedis = redisTemplate.boundValueOps(String.format(cacheKeyFormat,n,k));
        return strRedis.get();
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasKeyCacheAble(String n,String k) {
        try {
            return redisTemplate.hasKey(String.format(cacheKeyFormat,n,k));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String cacheAbleKey(String n,String k){
        return String.format(cacheKeyFormat,n,k);
    }

}
