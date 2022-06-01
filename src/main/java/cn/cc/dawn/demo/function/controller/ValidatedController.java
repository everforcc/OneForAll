package cn.cc.dawn.demo.function.controller;

import cn.cc.dawn.demo.function.dto.ValidatedDto;
import cn.cc.dawn.demo.function.service.ValidatedService;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/demo/filesystem")
@RestController
public class ValidatedController {

    @Autowired
    ValidatedService validatedService;

    @PostMapping("/exception")
    public ResultE<String> feignClient(@RequestBody String json){
        return new ResultE<String>().execute(e ->{
            ValidatedDto validatedDto = JSONObject.parseObject(json, ValidatedDto.class);
            e.setSuccess(validatedService.testPattern(validatedDto));
        });
    }

}
