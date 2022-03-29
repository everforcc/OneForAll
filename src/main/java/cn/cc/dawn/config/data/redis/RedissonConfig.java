package cn.cc.dawn.config.data.redis;

import cn.cc.dawn.config.init.yml.ConfigurationSpring;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 参考文档
 * [Redisson教程github](https://github.com/redisson/redisson/wiki/5.-单个集合数据分片（Sharding）)
 * [多种链接模式](https://blog.csdn.net/lkx94/article/details/76619544)
 * []()
 */
@Configuration
public class RedissonConfig {


    /**
     * 待办
     * 集群
     * 内存大小
     */

    @Autowired
    ConfigurationSpring configurationSpring;

    @Bean
    public RedissonClient redissonClientSingle() {
        Config config = new Config();
        ConfigurationSpring.Redis redisConfig = configurationSpring.getRedis();
        config.useSingleServer().setAddress("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort() + "")
                .setPassword(redisConfig.getPassword())
                .setDatabase(redisConfig.getDatabase());
        /**
         * 设置编码，防止查看的时候乱码
         */
        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }

    public static void main(String[] args) {
        // 示例代码

        RedissonClient redissonClient = null;
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("myLong");

        // sync way 同步
        rAtomicLong.compareAndSet(3, 401);
        // async way 异步
        rAtomicLong.compareAndSetAsync(3, 401);

        RedissonReactiveClient redissonReactiveClient = null;
        RAtomicLongReactive rAtomicLongReactive = redissonReactiveClient.getAtomicLong("myLong");

        // reactive way 响应
        rAtomicLongReactive.compareAndSet(3, 401);
    }

    /**
     * 1. 集群
     */
//    public RedissonClient redissonClientCluster() {
//        Config config = new Config();
//        config.useClusterServers()
//                .setScanInterval(2000) // cluster state scan interval in milliseconds
//                .addNodeAddress("redis://127.0.0.1:6380", "redis://127.0.0.1:6381")
//                .addNodeAddress("redis://127.0.0.1:6382");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }

    /**
     * 2. 单例模式
     */
//    public RedissonClient redissonClientSingle() {
//        // connects to 127.0.0.1:6379 by default
//        // RedissonClient redisson = Redisson.create();
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }

    /**
     * 3. 哨兵模式
     */
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName("mymaster")
//                .addSentinelAddress("redis://127.0.0.1:26389", "redis://127.0.0.1:26379")
//                .addSentinelAddress("redis://127.0.0.1:26319");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }

    /**
     * 4. 主从模式
     */
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                .setMasterAddress("redis://127.0.0.1:6379")
//                .addSlaveAddress("redis://127.0.0.1:6389", "redis://127.0.0.1:6332", "redis://127.0.0.1:6419")
//                .addSlaveAddress("redis://127.0.0.1:6399");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }

    /**
     *
     */

}
