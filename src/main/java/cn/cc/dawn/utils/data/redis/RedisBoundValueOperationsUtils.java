package cn.cc.dawn.utils.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisBoundValueOperationsUtils {

    /**
     * 直接看文档和示例用就好了
     */

    /**
     * 其他方法看文档
     */
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    //@Resource(name="redisTemplate")
    //private ListOperations<String, String> listOps;

    /*public void addKV(String k, String v) {
        listOps.leftPush(k, v);
        //BoundValueOperations<String, String> strRedis = template.boundValueOps("key");
    }*/

    public boolean addKV(String k, String v) {
        BoundValueOperations<String, String> strRedis = template.boundValueOps(k);
        //判断key是否有对应的value,如果有,则返回false,如果没有,添加,返回true
        return strRedis.setIfAbsent(v, Duration.ofSeconds(60));
    }

    public void removeK(String k) {
        BoundValueOperations<String, String> strRedis = template.boundValueOps(k);
        strRedis.persist();
    }

    public String getValue(String k) {
        BoundValueOperations<String, String> strRedis = template.boundValueOps(k);

        return strRedis.get();
    }

}
