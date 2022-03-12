package cn.cc.dawn.config.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class StringMapTranConvert implements AttributeConverter<Map<String,String>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String,String> stringMap) {
        return JSON.toJSONString(stringMap);
    }

    @Override
    public Map<String,String> convertToEntityAttribute(String s) {
        return JSON.parseObject(s, new TypeReference<Map<String,String>>() {});
    }
}
