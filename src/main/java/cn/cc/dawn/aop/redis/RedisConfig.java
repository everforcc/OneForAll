package cn.cc.dawn.aop.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guokailong 2021-10-25
 */
public class RedisConfig {

    public static Map<String,Integer> map = new HashMap<>();
    public static int defalutTime = 6000;
    public static int leastTime = 1000;

    /*public static String time(int time){
        return time + "";
    }*/

}
