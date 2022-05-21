package cn.cc.dawn.utils.data.redis;

import java.time.Duration;

public interface IRedisTemplate {

    boolean addKV(String k, String v, Duration timeout);

    String getValue(String k);

}
