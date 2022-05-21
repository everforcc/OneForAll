package cn.cc.dawn.utils.check;

import cn.cc.dawn.utils.constant.LogConstant;

import java.util.Objects;

public class ObjectUtils {

    /**
     * 是否为空
     */

    public static boolean isNull(Object obj){
        return Objects.isNull(obj);
    }
    public static boolean nonNull(Object obj){
        return Objects.nonNull(obj);
    }

    public static <T> T requireNonNull(T obj,String errMsg){
        return Objects.requireNonNull(obj,errMsg);
    }

    public static <T> T requireNonNull(T obj){
        return Objects.requireNonNull(obj);
    }

    public static boolean equals(Object a,Object b){
        return Objects.equals(a,b);
    }

    public static void main(String[] args) {
        System.out.println(LogConstant.SPLIT + requireNonNull(null,"字符串不能为空"));
    }

}
