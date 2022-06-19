/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-18 09:04
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
