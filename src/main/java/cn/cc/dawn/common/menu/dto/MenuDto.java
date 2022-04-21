package cn.cc.dawn.common.menu.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.MenuEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuDto extends CommonFiledDto {

    private int parentid;
    private String parentuuid;
    private MenuEnum type;
    private String name;
    private String auth;

    /**
     * TODO 修改数据是否要保存历史,待定
     */
    private String version;

}
