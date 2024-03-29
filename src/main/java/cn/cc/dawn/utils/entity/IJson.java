package cn.cc.dawn.utils.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

public interface IJson {

    /**
     * 当前类转换为Json字符串
     *
     * @param features {@link SerializerFeature}
     * @return String
     */
    @JSONField(serialize = false, deserialize = false)
    default String json(SerializerFeature... features) {
        return JSON.toJSONString(this, features);
    }

    /**
     * 当前类转换为格式化后的Json字符串
     *
     * @return String
     */
    @JSONField(serialize = false, deserialize = false)
    default String jsonFormat() {
        return json(PrettyFormat);
    }

    /**
     * 当前类转换为 JSONObject 对象
     *
     * @return JSONObject
     */
    @JSONField(serialize = false, deserialize = false)
    default JSONObject jsonObject() {
        return (JSONObject) JSON.toJSON(this);
    }

    /**
     * 填充初始值，并转换为 json 字符串
     *
     * @return JSONObject
     */
    @JSONField(serialize = false, deserialize = false)
    default String jsonDefaultValue() {
        return JSON.toJSONString(this,
                WriteMapNullValue,
                WriteNullBooleanAsFalse,
                WriteNullListAsEmpty,
                WriteNullNumberAsZero,
                WriteNullStringAsEmpty
        );
    }

}
