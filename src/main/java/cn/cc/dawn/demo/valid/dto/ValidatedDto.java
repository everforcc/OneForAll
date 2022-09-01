package cn.cc.dawn.demo.valid.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import cn.cc.dawn.utils.enums.RegexCommonEnum;
import cn.cc.dawn.utils.i.validated.ISave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 也可以继承自定义实现
 */
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

    /**
     * integer 校验示例
     */
    @Min(0)
    @Max(100)
    @NotNull
    private Integer age;


    private Integer intEnum;

    @Digits(integer = 3, fraction = 2)
    @NotNull
    private Double weight;

    @DecimalMin("0.00")
    @DecimalMax("2.00")
    @NotNull
    private BigDecimal bigDecimal;

    @NotEmpty
    @Size(min = 1,max = 2,message = "list个数超出期望范围")
    private List<String> stringList;

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
