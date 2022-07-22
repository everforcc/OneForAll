package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.constant.DateFormatConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 时间工具类
 */
public class RSimpleDateFormat {

    /**
     * 根据正则返回时间
     *
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

    /**
     * 严格校验日期是否为正确格式
     *
     * @param dateStr 日期字符串
     * @param pattern 正则
     * @return 校验结果
     */
    public static boolean checkStrEffect(String dateStr, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            // 设置严格校验， 否则 20220229 也会通过
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormatConstant.yyyyMMdd);
        String dateStr = "20220228";
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateStr);
            System.out.println(true);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }

}
