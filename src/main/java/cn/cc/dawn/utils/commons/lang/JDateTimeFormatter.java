/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-26 17:02
 * Copyright
 */

package cn.cc.dawn.utils.commons.lang;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;

/**
 * 格式化时间
 */
public class JDateTimeFormatter {

    private static LocalDateTime endTime = LocalDateTime.of(2023,5,1,0,0);

    public static Date parse(String dateStr, String pattern) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            dateTimeFormatter.withResolverStyle(ResolverStyle.STRICT);
            dateTimeFormatter.withChronology(IsoChronology.INSTANCE);

            LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
            // 设置系统默认时区
            ZoneId zoneId = ZoneId.systemDefault();
            Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("日期格式化异常: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LocalDateTime nowTime= LocalDateTime.now();
        System.out.println(nowTime.isBefore(endTime));
        LocalDateTime testTime = LocalDateTime.of(2023,5,1,0,1);
        System.out.println(endTime.isBefore(endTime));
        System.out.println(testTime.isBefore(endTime));
    }

}
