package cn.cc.dawn.utils;

public class RandomUtils {

    public static int randomInt(Integer start, Integer end){
        //start = start - end;
        return org.apache.commons.lang3.RandomUtils.nextInt(start, end);
    }

    public static double getRandomDouble(double start,double end){
        return org.apache.commons.lang3.RandomUtils.nextDouble(start,end);
    }

    public static void main(String[] args) {
        System.out.println(getRandomDouble(0.000d,0.004d-0));
    }

}
