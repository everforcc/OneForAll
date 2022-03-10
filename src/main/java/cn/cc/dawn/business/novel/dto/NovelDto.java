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
public class NovelDto extends CommonFiledDto {

    private String name;

    private String sourceurl;

    private String sourcename;

    // 缺一个 html id

}
