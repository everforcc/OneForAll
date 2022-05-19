/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-18 09:04
 * Copyright
 */

package cn.cc.dawn.config.convert;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class StringListTranConvert  implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return  String.join(",",stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String str) {
        return Arrays.asList(str.split(","));
    }
}
