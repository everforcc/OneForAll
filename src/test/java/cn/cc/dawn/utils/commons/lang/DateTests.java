/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-06-01 14:53
 * Copyright
 */

package cn.cc.dawn.utils.commons.lang;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class DateTests {

    @Test
    public void before(){
        Date date = new Date();

        String behand = "20220606";
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMdd");
        Date nextDate = null;
        try {
            nextDate =  fastDateFormat.parse(behand);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.after(nextDate));


    }

}
