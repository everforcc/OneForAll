/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-26 10:58
 * Copyright
 */

package jdk.instance;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstanceofBase {

    private String base;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat);
    }
}
