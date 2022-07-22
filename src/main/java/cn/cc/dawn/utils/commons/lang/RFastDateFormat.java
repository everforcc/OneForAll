/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 14:24
 */

package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.constant.DateFormatConstant;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

public class RFastDateFormat {


    public static String format(Date date){
        return format(date, DateFormatConstant.yyyy_MM_dd_2);
    }

    public static String format(long time){
        return format(time,DateFormatConstant.yyyy_MM_dd_2);
    }

    /**
     *
     * @param date
     * @param pattern {@link DateFormatConstant }
     */
    public static String format(Date date,String pattern){
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        return fastDateFormat.format(date);
    }

    public static String format(long time,String pattern){
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        return fastDateFormat.format(time);
    }

    public static String format(String time,String pattern){
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        return fastDateFormat.format(time);
    }

    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(format(date,DateFormatConstant.yyyy_MM_dd_2));
//        System.out.println(format(date.getTime(),DateFormatConstant.yyyy_MM_dd_2));
        String dateStr = "20220299";

        FastDateFormat fastDateFormat = FastDateFormat.getInstance(DateFormatConstant.yyyyMMdd);

        try {
            Date date = fastDateFormat.parse(dateStr);
            System.out.println(date.toString());
            System.out.println(date.getYear());
            System.out.println(date.getMonth());
            System.out.println(date.getDay());
        }catch (IllegalArgumentException  illegalArgumentException){
            illegalArgumentException.printStackTrace();
            System.err.println(illegalArgumentException.getMessage());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }

}
