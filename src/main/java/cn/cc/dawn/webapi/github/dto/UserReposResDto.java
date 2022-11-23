/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-02 18:25
 * Copyright
 */

package cn.cc.dawn.webapi.github.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReposResDto {

    private String id;
    private String name;
    private String full_name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String created_at;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String updated_at;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat);
    }
}
