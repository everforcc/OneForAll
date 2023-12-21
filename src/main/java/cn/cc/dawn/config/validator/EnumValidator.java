package cn.cc.dawn.config.validator;

import cn.cc.dawn.utils.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description
 * @Author everforcc
 * @Date 2023-12-21 16:42
 * Copyright
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {

    /* 字符串枚举值 */
    private String[] strValues;

    /* 整形枚举值 */
    private int[] intValues;

    /* 长整型枚举值 */
    private long[] longValues;

    @Override
    public void initialize(Enum constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        this.strValues = constraintAnnotation.strValues();
        this.intValues = constraintAnnotation.intValues();
        this.longValues = constraintAnnotation.longValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            for (String s : this.strValues) {
                if (s.equals(value)) {
                    return true;
                }
            }
        } else if (value instanceof Integer) {
            for (int s : this.intValues) {
//                if (s == ((Integer) value).intValue()) {
                if (s == (Integer) value) {
                    return true;
                }
            }
        } else if (value instanceof Long) {
            for (long s : this.longValues) {
                if (s == (Long) value) {
                    return true;
                }
            }
        }
        return false;
    }
}
