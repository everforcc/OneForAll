/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-19 16:58
 * Copyright
 */

package cn.cc.dawn.config.validator;

import cn.cc.dawn.utils.annotation.PageSize;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数据枚举格式校验
 */
@Slf4j
public class PageSizeValidator implements ConstraintValidator<PageSize, Object> {

    private long[] longs = {1L, 2L, 3L};

    /**
     * 重写校验方法
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return 校验结果
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof Long) {
            for (long l : longs) {
                if (l == Long.parseLong(String.valueOf(value))) {
                    return true;
                }
            }
        }
        log.error("信息不匹配: 【{}】", value);
        return false;
    }
}
