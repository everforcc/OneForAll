/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-26 09:47
 */

package cn.cc.dawn.utils.commons.lang;

import java.util.Date;

public class RDateUtils {

    public static double betweenDateSecond(Date startDate, Date endDate){
        return (endDate.getTime() - startDate.getTime())/1000d;
    }

}
