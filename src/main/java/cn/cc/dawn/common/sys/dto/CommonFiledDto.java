package cn.cc.dawn.common.sys.dto;

import cn.cc.dawn.utils.enums.impl.StatusEnum;
import cn.cc.dawn.utils.inter.valited.ISave;
import cn.cc.dawn.utils.inter.valited.IUpdate;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

//@Data 会重写toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonFiledDto {

    @NotNull(groups = {IUpdate.class},message = "id不允许为null")
    private int id;
    @NotNull(groups = {IUpdate.class},message = "uuid不允许为null")
    private String uuid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    /**
     * 默认为有效，如果忘记写就是有效
     */
    private StatusEnum effect = StatusEnum.EFFECT;
    private StatusEnum status = StatusEnum.EFFECT;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
