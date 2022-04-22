package cn.cc.dawn.open.novel.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
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
