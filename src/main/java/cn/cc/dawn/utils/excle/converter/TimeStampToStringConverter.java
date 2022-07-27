/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-27 22:57
 * Copyright
 */

package cn.cc.dawn.utils.excle.converter;

import cn.cc.dawn.utils.commons.lang.RFastDateFormat;
import cn.cc.dawn.utils.constant.DateFormatConstant;
import com.github.crab2died.converter.WriteConvertible;

import java.util.Date;

/**
 * excle转换器
 * 将timestamp转换为时间字符串
 */
public class TimeStampToStringConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object o) {
        Long l = (Long) o;
        Date date = new Date(l * 1000);
        return RFastDateFormat.format(date, DateFormatConstant.yyyy_MM_dd_HH_mm_ss);
    }
}
