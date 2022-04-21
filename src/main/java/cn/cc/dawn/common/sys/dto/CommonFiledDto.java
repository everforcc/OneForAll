package cn.cc.dawn.common.sys.dto;

import cn.cc.dawn.utils.enums.impl.StatusEnum;
import cn.cc.dawn.utils.inter.valited.ISave;
import cn.cc.dawn.utils.inter.valited.IUpdate;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

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
    private int createUserid;
    private int updateUserid;

    /**
     * 默认为有效，如果忘记写就是有效
     */
    private StatusEnum effect = StatusEnum.EFFECT;
    private StatusEnum status = StatusEnum.EFFECT;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public CommonFiledDto(Integer userid) {
        this.createUserid = userid;
        this.updateUserid = userid;
        this.uuid = UUID.randomUUID().toString().replace("-", "");;
        this.createTime = nowTime();
        this.updateTime = nowTime();
        this.effect = StatusEnum.EFFECT;
        this.status = StatusEnum.EFFECT;
    }

    public static CommonFiledDto save(Object userid){
        return new CommonFiledDto( Integer.parseInt(userid.toString()));
    }

    public static Timestamp nowTime(){
        return new Timestamp(new Date().getTime());
    }

}
