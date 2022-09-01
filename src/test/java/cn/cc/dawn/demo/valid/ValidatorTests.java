/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-05 17:32
 * Copyright
 */

package cn.cc.dawn.demo.valid;

import cn.cc.dawn.demo.valid.dto.ValidatedDto;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 *  对这个源码的学习
 */
public class ValidatorTests {

    @Test
    public void validator_learn(){
        ValidatedDto validatedDto = new ValidatedDto();
        // ValidatorImpl
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ValidatedDto>> constraintViolationSet = validator.validateProperty(validatedDto,"strLength");
        constraintViolationSet.forEach(e ->
                System.out.println("参数名: " + e.getPropertyPath() + " + \r\n " + " 错误信息: " + e.getMessage() ));
    }

}
