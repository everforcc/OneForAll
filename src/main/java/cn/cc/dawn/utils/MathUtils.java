package cn.cc.dawn.utils;

import org.apache.commons.lang3.RandomUtils;

public class MathUtils {

    public static int getRandomInt(Integer start,Integer end){

        start = start - end;
        return RandomUtils.nextInt(start, end);
    }

    public static double getRandomDouble(double start,double end){
        return RandomUtils.nextDouble(start,end);
    }

    public static void main(String[] args) {
        System.out.println(getRandomDouble(0.000d,0.004d-0));
    }

}
