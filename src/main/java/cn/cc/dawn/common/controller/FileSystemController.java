package cn.cc.dawn.common.controller;

import cn.cc.dawn.common.dto.FileSystemDto;
import cn.cc.dawn.common.service.FileSystemService;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/common/filesystem")
@RestController
public class FileSystemController {

    @Autowired
    FileSystemService fileSystemService;

    @PostMapping("/exception")
    public ResultE<String> feignClient(@RequestBody String json){
        return new ResultE<String>().execute(e ->{
            FileSystemDto fileSystemDto = JSONObject.parseObject(json,FileSystemDto.class);
            e.setSuccess(fileSystemService.testPattern(fileSystemDto));
        });
    }

}
