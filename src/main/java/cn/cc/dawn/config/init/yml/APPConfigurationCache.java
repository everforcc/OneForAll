package cn.cc.dawn.config.init.yml;

import cn.cc.dawn.config.cache.AppCache;
import cn.cc.dawn.local.craw.business.bdwp.dto.BDRegisterDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 缓存配置
 * 管理@Cacheable的缓存，
 * 如果加新的，必须在 cacheManager 里面添加
 */
@EnableConfigurationProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "app.cache")
public class APPConfigurationCache {


    /**
     * 用户token的缓存
     */
    AppCache customerUserToken;

    /**
     * 测试redis缓存
     */
    AppCache redisCacheTest;

    AppCache WebDataCacheMain;

    AppCache BDRegister;

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {

        final RedisCacheManager.RedisCacheManagerBuilder cacheManagerBuilder = RedisCacheManager.builder(redisConnectionFactory);
        return cacheManagerBuilder
                // 设置指定类型key的参数
                .withCacheConfiguration(customerUserToken.getCachekey(), RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(customerUserToken.getExpired()) // 设置过期时间
                        .disableCachingNullValues() // 禁止缓存 null 值
                        /**
                         * 设置按照哪种方式序列化，以及之后恢复为哪种格式
                         * RedisSerializer StringRedisSerializer FastJsonRedisSerializer
                         * 根据数据格式选择序列化方式
                         * 测试使用的是string，如果用json的话就会当作json来处理多引号，选用stringredis就好
                         */
                        //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(String.class)))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))

                ).withCacheConfiguration(redisCacheTest.getCachekey(), RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(redisCacheTest.getExpired()) // 设置过期时间
                        .disableCachingNullValues() // 禁止缓存 null 值
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))

                ).withCacheConfiguration(WebDataCacheMain.getCachekey(), RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(WebDataCacheMain.getExpired()) // 设置过期时间
                        .disableCachingNullValues() // 禁止缓存 null 值
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(WebSiteDto.class)))
                )
                .withCacheConfiguration(BDRegister.getCachekey(), RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(BDRegister.getExpired()) // 设置过期时间
                        .disableCachingNullValues() // 禁止缓存 null 值
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(BDRegisterDto.class)))
                )
                .build();
    }

}
