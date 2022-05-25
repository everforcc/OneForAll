package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.constant.DateFormatConstant;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 时间工具类
 */
public class RSimpleDateFormat {

    /**
     * 根据正则返回时间
     * @param regex
     * @return
     */
    public static String nowTime(String regex) {
        return new SimpleDateFormat(regex).format(new Date(System.currentTimeMillis()));
    }

    public static String nowTime(long timeMillis) {
        return new SimpleDateFormat(DateFormatConstant.yyyy_MM_dd_2).format(new Date(timeMillis));
    }

    public static String nowTime() {
        return new SimpleDateFormat(DateFormatConstant.yyyy_MM_dd_2).format(new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
    }

}
