package cn.cc.dawn.demo.valid.controller;

import cn.cc.dawn.demo.valid.dto.ValidatedDto;
import cn.cc.dawn.demo.valid.service.ValidatedService;
import cn.cc.dawn.utils.annotation.EnumsValited;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/open/validated")
@RestController
@Slf4j
@Validated
public class ValidatedController {

    @Autowired
    ValidatedService validatedService;

    @PostMapping("/validate")
    public ResultE<String> validate(@RequestBody String json){
        return new ResultE<String>().execute(e ->{
            ValidatedDto validatedDto = JSONObject.parseObject(json, ValidatedDto.class);
            e.setSuccess(validatedService.testValidate(validatedDto));
        });
    }

    @PostMapping("/validateWithOut")
    public ResultE<String> validateWithOut(@RequestBody String json){
        return new ResultE<String>().execute(e ->{
            ValidatedDto validatedDto = JSONObject.parseObject(json, ValidatedDto.class);
            e.setSuccess(validatedService.testValidateWithOut(validatedDto));
        });
    }

    /**
     * 校验自定义枚举校验
     * @param pageSize 大小
     * @return 返回结果
     */
    @GetMapping("/ps/{pageSize}")
    public ResultE<String> ps(@EnumsValited @PathVariable Long pageSize){
        return new ResultE<String>().execute(e ->{
            log.info("pageSize: 【{}】",pageSize);
        });
    }

}
