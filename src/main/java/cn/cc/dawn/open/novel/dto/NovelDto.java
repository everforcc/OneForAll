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
public class NovelDto extends CommonFiledDto {

    private String name;

    private String sourceurl;

    private String sourcename;

    // 缺一个 html id

}
