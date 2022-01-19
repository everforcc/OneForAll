package cn.cc.dawn.utils;

import org.apache.commons.lang3.RandomUtils;

public class MathUtils {

    public static int getRandom(int start,int end){
        return RandomUtils.nextInt(start, end);
    }

}
