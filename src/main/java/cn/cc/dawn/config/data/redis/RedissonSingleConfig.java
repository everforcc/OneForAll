//package cn.cc.dawn.config.data.redis;
//
//import org.redisson.Redisson;
//import org.redisson.api.RAtomicLong;
//import org.redisson.api.RAtomicLongReactive;
//import org.redisson.api.RedissonClient;
//import org.redisson.api.RedissonReactiveClient;
//import org.redisson.config.ClusterServersConfig;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 参考文档
// * [Redisson教程github](https://github.com/redisson/redisson/wiki/5.-单个集合数据分片（Sharding）)
// * [多种链接模式](https://blog.csdn.net/lkx94/article/details/76619544)
// * []()
// */
//@Configuration
//public class RedissonSingleConfig {
//
//    @Value("${spring.redis.password}")
//    private String password;
//
////    @Value("${spring.redis.timeout}")
////    private int timeout;
////
////    @Value("${spring.redis.cluster.max-redirects}")
////    private int maxRedirects;
//
//    //
////    @Bean
////    public RedissonClient redissonClientSingle() {
////        Config config = new Config();
////        ConfigurationSpring.Redis redisConfig = configurationSpring.getRedis();
////        config.useSingleServer().setAddress("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort() + "")
////                .setPassword(redisConfig.getPassword())
////                .setDatabase(redisConfig.getDatabase());
////        /**
////         * 设置编码，防止查看的时候乱码
////         */
////        Codec codec = new JsonJacksonCodec();
////        config.setCodec(codec);
////        RedissonClient redisson = Redisson.create(config);
////
////        return redisson;
////    }
//
//    /**
//     * 2. 单例模式
//     */
//    @Bean
//    public RedissonClient redissonClientSingle() {
//        // connects to 127.0.0.1:6379 by default
//        // RedissonClient redisson = Redisson.create();
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        return Redisson.create(config);
//    }
//
//}
