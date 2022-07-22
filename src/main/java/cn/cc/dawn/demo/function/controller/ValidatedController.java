package cn.cc.dawn.demo.function.controller;

import cn.cc.dawn.demo.function.dto.ValidatedDto;
import cn.cc.dawn.demo.function.service.ValidatedService;
import cn.cc.dawn.utils.annotation.EnumsValited;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/demo/validated")
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
