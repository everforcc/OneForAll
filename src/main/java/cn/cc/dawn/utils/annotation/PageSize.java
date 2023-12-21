package cn.cc.dawn.utils.annotation;

import cn.cc.dawn.config.validator.PageSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 加入到 validation 框架的枚举内容
 * 数据枚举格式校验
 */
// @Target({METHOD, FIELD, CONSTRUCTOR, ANNOTATION_TYPE, PARAMETER})
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PageSizeValidator.class)
public @interface PageSize {

    /* 错误信息 */
    String message() default "每页仅显示 1, 2, 3 条数据";

    /* 这两个参数不写报错 */
    /* contains Constraint annotation, but does not contain a groups parameter. */
    Class<?>[] groups() default {};

    /* 负载 */
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        PageSize[] value();
    }

}
