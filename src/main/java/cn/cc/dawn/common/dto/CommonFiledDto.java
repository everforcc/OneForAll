package cn.cc.dawn.common.dto;

import cn.cc.dawn.common.enums.StatusEnum;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.sql.Timestamp;

//@Data 会重写toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonFiledDto {

    private int id;
    private String uuid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    private StatusEnum effect;
    private StatusEnum status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
