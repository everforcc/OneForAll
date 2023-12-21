package cn.cc.dawn.utils.annotation;

import cn.cc.dawn.config.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 加入到 validation 框架的美剧内容
 */
@Target({METHOD, FIELD, CONSTRUCTOR, ANNOTATION_TYPE, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface Enum {

    /* 错误信息 */
    String message() default "必须为指定值";

    /* 字符串枚举值 */
    String[] strValues() default {};

    /* 整型枚举值 */
    int[] intValues() default {};

    /* 长整型枚举值 */
    long[] longValues() default {};

    /* 分组 */
    Class<?>[] groups() default {};

    /* 负载 */
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Enum[] value();
    }

}
