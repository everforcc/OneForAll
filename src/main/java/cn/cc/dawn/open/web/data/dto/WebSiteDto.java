package cn.cc.dawn.open.web.data.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import lombok.*;

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
    private String webroot;
    private String webname;
    private String softtype;
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
    private int vip;

}
