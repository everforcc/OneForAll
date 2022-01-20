package cn.cc.dawn.demo.data.redis.service;

import cn.cc.dawn.config.cache.impl.TestCache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author guokailong 2021-10-25
 */
@Service("redisCacheService")
public class RedisCacheService {

    /**
     * 如果有就返回，如果没就添加
     * @param str
     * @return
     */
    @Cacheable(cacheNames = TestCache.TestCacheKey,key = "#str")
    public String redisCacheable(String str){
        System.out.println("debug:" + str);
        return "abc" + str;
    }

    /**
     * 有条件的插入
     * @param str
     * @return
     */
    @Cacheable(cacheNames = TestCache.TestCacheKey,key = "#str",condition = "#str.length() > 1")
    public String redisCacheableCondition(String str){
        System.out.println("debug:" + str);
        return "abc" + str;
    }

    /**
     * 3. 不检查就插入并覆盖
     * @param str
     * @return
     */
    @CachePut(cacheNames = TestCache.TestCacheKey,key = "#str")
    public String redisCachePut(String str){
        System.out.println("debug:" + str);
        return "更新:" + str;
    }

    /**
     * 4. 删除key
     * @param str
     * @return
     */
   @CacheEvict(cacheNames = TestCache.TestCacheKey,key = "#str")
    public String redisCacheEvict(String str){
        System.out.println("删除:" + str);
        return str;
    }

}
