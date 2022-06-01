package cn.cc.dawn.common.menu.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.i.validated.ISave;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MenuPicDataDto extends CommonFiledDto {

    /**
     * 隶属于哪个分类
     */
    @NotNull(message = "上级id不能为空", groups = ISave.class)
    private String parentuuid;
    /**
     * 图片 id
     */
    @NotNull(message = "图片id不能为空", groups = ISave.class)
    private String picuuid;


}
