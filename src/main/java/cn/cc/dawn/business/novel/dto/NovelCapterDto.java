package cn.cc.dawn.business.novel.dto;

import cn.cc.dawn.common.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovelCapterDto extends CommonFiledDto {

    private int parentid;

    private int contentid;

    private String captername;

    // TODO 随后处理为枚举
    private int contenttype;

    private String sourceurl;

    private String content;
}
