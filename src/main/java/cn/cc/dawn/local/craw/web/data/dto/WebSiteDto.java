package cn.cc.dawn.local.craw.web.data.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.StatusEnum;
import cn.cc.dawn.utils.inter.valited.ISave;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDto extends CommonFiledDto {

    /**
     * 字段
     * 1. 是否需要vip
     * 2.
     */

    // TODO 网址最后斜线 / 问题

    @NotNull(groups = {ISave.class},message = "webroot不允许为null")
    private String webroot;
    @NotNull(groups = {ISave.class},message = "webname不允许为null")
    private String webname;
    @NotNull(groups = {ISave.class},message = "softtype不允许为null")
    private String softtype;
    @NotNull(groups = {ISave.class},message = "webtype不允许为null")
    private String webtype;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String cookie;
    private String safequestion;
    private String encrypt;
    private String discription;

    /**
     * 默认1 需要
     * 2不需要，需要处理历史数据
     */
    @NotNull(groups = {ISave.class},message = "vip不允许为null")
    private StatusEnum vip = StatusEnum.UNEFFECT;

    public void setVip(String vip) {
        if(StatusEnum.EFFECT.equals(vip)){
            this.vip = StatusEnum.EFFECT;
        }
    }
}
