package cn.cc.dawn.demo.data.redis.service;

import cn.cc.dawn.demo.data.redis.cache.RedisCacheTestCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author guokailong 2021-10-25
 */
@Slf4j
@Service("redisCacheService")
public class RedisCacheService {

    /**
     * 如果有就返回，如果没就添加
     * @param str
     * @return
     */
    @Cacheable(cacheNames = RedisCacheTestCache.REDISCACHETEST,key = "#str")
    public String redisCacheable(String str){
        log.info("debug:" + str);
        return "abc" + str;
    }

    /**
     * 有条件的插入
     * @param str
     * @return
     */
    @Cacheable(cacheNames = RedisCacheTestCache.REDISCACHETEST,key = "#str",condition = "#str.length() > 1")
    public String redisCacheableCondition(String str){
        log.info("debug:" + str);
        return "abc" + str;
    }

    /**
     * 3. 不检查就插入并覆盖
     * @param str
     * @return
     */
    @CachePut(cacheNames = RedisCacheTestCache.REDISCACHETEST,key = "#str")
    public String redisCachePut(String str){
        log.info("debug:" + str);
        return "更新:" + str;
    }

    /**
     * 4. 删除key
     * @param str
     * @return
     */
   @CacheEvict(cacheNames = RedisCacheTestCache.REDISCACHETEST,key = "#str")
    public String redisCacheEvict(String str){
        log.info("删除:" + str);
        return str;
    }

}
