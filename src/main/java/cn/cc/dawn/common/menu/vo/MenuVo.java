package cn.cc.dawn.common.menu.vo;

import cn.cc.dawn.utils.enums.impl.MenuEnum;
import cn.cc.dawn.utils.i.valited.ISave;
import cn.cc.dawn.utils.i.valited.IUpdate;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MenuVo {

    /**
     * 传给前端用
     */
    @NotNull(groups = {IUpdate.class},message = "uuid不能为空")
    private String uuid;
    @NotNull(groups = {ISave.class},message = "parentuuid 不能为空")
    private String parentuuid;
    @NotNull(groups = {ISave.class},message = "目录名 不能为空")
    private String name;
    @NotNull(groups = {ISave.class},message = "类型 不能为空")
    private MenuEnum type;

    private String auth;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
