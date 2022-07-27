/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-01 14:53
 */

package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.constant.DateFormatConstant;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class RFastDateFormatTests {

    @Test
    public void before(){
        Date date = new Date();

        String behand = "20220606";
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(DateFormatConstant.yyyyMMdd);
        Date nextDate = null;
        try {
            nextDate =  fastDateFormat.parse(behand);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.after(nextDate));


    }

}
