package cn.cc.dawn.config.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.persistence.AttributeConverter;

public class StringAryTranConvert implements AttributeConverter<String[], String> {
    @Override
    public String convertToDatabaseColumn(String[] strings) {
        return JSON.toJSONString(strings);
    }

    @Override
    public String[] convertToEntityAttribute(String s) {
        return JSON.parseObject(s, new TypeReference<String[]>() {});
    }
}
