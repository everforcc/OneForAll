package cn.cc.dawn.open.novel.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovelDto extends CommonFiledDto {

    private String name;

    private String sourceurl;

    private String sourcename;

    // 缺一个 html id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NovelDto novelDto = (NovelDto) o;
        return Objects.equals(sourceurl, novelDto.sourceurl) && Objects.equals(sourcename, novelDto.sourcename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceurl, sourcename);
    }
}
