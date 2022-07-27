/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-26 09:42
 * Copyright
 */

package cn.cc.dawn.utils.commons.lang;

import cn.cc.dawn.utils.commons.regex.RegexUtils;
import cn.cc.dawn.utils.constant.DateFormatConstant;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSimpleDateFormatTests {

    @Test
    public void specialChar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormatConstant.yyyyMMdd);
        String dateStr = "20220227,20220228";
        boolean flag = RegexUtils.isMatches("\\d{8}", dateStr);

        System.out.println("是否是全数字: " + flag);

        try {
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(dateStr);
            System.out.println(true);
            System.out.println(simpleDateFormat.format(date));
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
