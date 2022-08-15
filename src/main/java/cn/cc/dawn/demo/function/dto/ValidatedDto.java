package cn.cc.dawn.demo.function.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.RegexCommonEnum;
import cn.cc.dawn.utils.i.validated.ISave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedDto extends CommonFiledDto {

    /**
     * @see RegexCommonEnum
     */
    @Pattern(regexp = "^((?!(\\<+|\\>+|\\/|\\\\+|\\|+|\\:+|\"+|\\*+|\\?+|\\；+|\\ +)).)*$", message = "文件/文件夹命名有误")
    private String fileName;

    /**
     * 文件大小用字节数来处理 byte[]
     */

    // ValidationMessages.properties
    @NotEmpty(groups = ISave.class)
    @Size(max = 10, message = " strLength 最多10位", groups = ISave.class)
    private String strLength;

    @NotEmpty
    @Size(max = 10, message = " withOutGroup 最多10位")
    private String withOutGroup;

    @Valid
    @NotNull(message = "内部对象: 不能为空", groups = ISave.class)
    InnerObj innerObj;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerObj {

        @NotEmpty(message = "内部对象: strLength 不能为空", groups = ISave.class)
        @Size(max = 10, message = "内部对象: strLength 最多10位", groups = ISave.class)
        private String strLength;

        @Pattern(regexp = "[a-zA-Z0-9.-]{1,3}", message = "内部对象: strRegex 不匹配", groups = ISave.class)
        private String strRegex;

        @NotEmpty
        @Size(max = 10, message = " withOutGroup 最多10位")
        private String withOutGroup;

    }

}
