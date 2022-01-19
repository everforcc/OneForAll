package cn.cc.dawn.config.data;

import cn.cc.dawn.config.yml.ConfigurationSpring;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Autowired
    ConfigurationSpring configurationSpring;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        config.useClusterServers()
//                .setScanInterval(2000)
//                .addNodeAddress("redis://127.0.0.1:6380", "redis://redis://127.0.0.1:6381")
//                .addNodeAddress("redis://redis://127.0.0.1:6382");

//        config.useClusterServers()
//                .setScanInterval(2000)
//                .addNodeAddress("redis://127.0.0.1:6380", "redis://127.0.0.1:6381")
//                .addNodeAddress("redis://127.0.0.1:6382");
//        RedissonClient redisson = Redisson.create(config);
        //RedissonClient redisson = Redisson.create();
        ConfigurationSpring.Redis redisConfig = configurationSpring.getRedis();
        //config.useSingleServer().setAddress("redis://180.76.156.43:6379").setPassword("c.c.5664").setDatabase(10);
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

}
