package cn.cc.dawn.utils;

public class StringUtils {


    public static String urlSubFileName(String url){
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
