package cn.cc.dawn.common.menu.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.impl.MenuEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuPicDto extends CommonFiledDto {

    /**
     * 主要是处理图片上传后的逻辑
     * pic的 目录逻辑
     */

    /**
     * 1. 哪个menu下的
     * 2. 分类 root/type/busi
     * 3. 到busi之后，再列一个表保存文件的位置
     *
     */
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
