package cn.cc.dawn.config.data.redis;

import cn.cc.dawn.config.init.yml.APPCacheConfiguration;
import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheManagerConfig {

    @Autowired
    APPCacheConfiguration appCacheConfiguration;

    //ConfigurationData configurationData;
    //ConfigurationData.UserCache userCache = configurationData.getTestCache();

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {

        CustomerUserCache userCache = appCacheConfiguration.getCustomuser();

        final RedisCacheManager.RedisCacheManagerBuilder cacheManagerBuilder = RedisCacheManager.builder(redisConnectionFactory);
        return cacheManagerBuilder
                // 设置指定类型key的参数
                .withCacheConfiguration(userCache.USER_TOKEN, RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(userCache.getExpired()) // 设置过期时间
                                .disableCachingNullValues() // 禁止缓存 null 值
                        /**
                         * 设置按照哪种方式序列化，以及之后恢复为哪种格式
                         * RedisSerializer StringRedisSerializer FastJsonRedisSerializer
                         * 根据数据格式选择序列化方式
                         * 测试使用的是string，如果用json的话就会当作json来处理多引号，选用stringredis就好
                         */
                         //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(String.class)))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))

                ).build();
    }

}
