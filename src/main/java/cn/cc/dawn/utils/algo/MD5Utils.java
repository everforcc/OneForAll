package cn.cc.dawn.utils.algo;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String md5Hex(byte[] bytes){
        return DigestUtils.md5Hex(bytes);
    }

    public static String md5Hex(String str){
        return DigestUtils.md5Hex(str);
    }

}
