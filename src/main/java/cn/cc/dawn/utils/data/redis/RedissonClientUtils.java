package cn.cc.dawn.utils.data.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonClientUtils {

//    private final RedissonClient redissonClient;
//    private final int expire_seconds = 60 * 2;
//    private final String string = "str";
//
//    public String set(String key){
//        /**
//         * 如果是token的话，可能会提前失效，如果遇到失效，业务逻辑重取一次
//         */
//        final RBucket<String> stringRBucket = redissonClient.getBucket(key);
//        if (stringRBucket.isExists()) {
//            return stringRBucket.get();
//        }else {
//            stringRBucket.set(string, expire_seconds, TimeUnit.SECONDS);
//            return string;
//        }
//
//    }

}
