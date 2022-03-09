package cn.cc.dawn.utils.date;

import java.util.Date;

public class DateUtils {

    public static String dateFormat(long timestap){
        return new Date(timestap).toString();
    }

}
