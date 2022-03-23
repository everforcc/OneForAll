package cn.cc.dawn.demo.function.dto;

import cn.cc.dawn.demo.craw.website.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.RegexCommonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedDto extends CommonFiledDto {

    /**
     * @see RegexCommonEnum
     */
    @Pattern(regexp = "^((?!(\\<+|\\>+|\\/|\\\\+|\\|+|\\:+|\"+|\\*+|\\?+|\\；+|\\ +)).)*$",message="文件/文件夹命名有误")
    private String name;

    /**
     * 文件大小用字节数来处理 byte[]
     */

}
