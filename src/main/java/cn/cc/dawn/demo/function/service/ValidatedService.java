package cn.cc.dawn.demo.function.service;

import cn.cc.dawn.demo.function.dto.ValidatedDto;
import cn.cc.dawn.utils.i.validated.ISave;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
@Transactional
public class ValidatedService {

    @Validated(ISave.class)
    public String testValidate(@Valid  ValidatedDto validatedDto) {//@NotNull(message = "【validatedDto】不能为null") final
        System.out.println(validatedDto.getFileName());
        System.out.println(validatedDto.getStrLength().length());

        validatedDto.setStrLength("aaaaaaaaaabbbbbbbbbb");

        return "";
    }

    @Validated
    public String testValidateWithOut(@Valid @NotNull(message = "【validatedDto】不能为null") final ValidatedDto validatedDto) {
        System.out.println(validatedDto.getFileName());
        System.out.println(validatedDto.getStrLength().length());

        validatedDto.setStrLength("aaaaaaaaaabbbbbbbbbb");

        return "";
    }

}
