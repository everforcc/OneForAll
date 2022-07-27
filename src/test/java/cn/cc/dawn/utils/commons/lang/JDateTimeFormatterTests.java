/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-26 17:07
 * Copyright
 */

package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.constant.DateFormatConstant;
import org.junit.Test;

import java.util.Date;

public class JDateTimeFormatterTests {

    @Test
    public void specialChar() {
        String str_1 = "20220228";
        // 非法日期
        String str_2 = "20220230";
        // 特殊字符
        String str_3 = "2022022@";
        // 组合
        String str_4 = "20220228,20220229";
        //Date date_1 = JDateTimeFormatter.parse(str_1, DateFormatConstant.yyyyMMdd);

        // 无法匹配,
        Date date_2 = JDateTimeFormatter.parse(str_2, DateFormatConstant.yyyyMMdd);
        System.out.println(date_2);

        // 可以校验到
        //Date date_3 =JDateTimeFormatter.parse(str_3, DateFormatConstant.yyyyMMdd);
        //JDateTimeFormatter.parse(str_4, DateFormatConstant.yyyyMMdd);
    }

}
