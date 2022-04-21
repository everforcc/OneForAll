package cn.cc.dawn.common.menu.dto;

import cn.cc.dawn.common.sys.dto.CommonFiledDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuPicDataDto extends CommonFiledDto {

    /**
     * 隶属于哪个分类
     */
    private String parentuuid;
    /**
     * 图片 id
     */
    private String picuuid;


}
