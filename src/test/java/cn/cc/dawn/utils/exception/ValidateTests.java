/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-08 15:12
 */

package cn.cc.dawn.utils.exception;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateTests {

    @Test
    public void validateMethodOneTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User("su", "tom", false);
        validator.validate(user).forEach(System.out::println);
    }

    // 查看结果
//    ConstraintViolationImpl{interpolatedMessage='名称长度必须在4和32之间', propertyPath=name, rootBeanClass=class com.qs.mmeng.hibernate.validator.constraints.interfacee.User, messageTemplate='名称长度必须在{min}和{max}之间'}
//    ConstraintViolationImpl{interpolatedMessage='邮箱格式不正确', propertyPath=email, rootBeanClass=class com.qs.mmeng.hibernate.validator.constraints.interfacee.User, messageTemplate='邮箱格式不正确'}
//    ConstraintViolationImpl{interpolatedMessage='只能为true', propertyPath=isStudent, rootBeanClass=class com.qs.mmeng.hibernate.validator.constraints.interfacee.User, messageTemplate='{javax.validation.constraints.AssertTrue.message}'}


}
