package cn.cc.dawn.local.craw.ys.controller;

import cn.cc.dawn.local.craw.ys.service.MYSYSService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 米游社数据
 */
@RequestMapping("/local/mys/ys")
@RestController
@Slf4j
public class MYSYSController {

    @Autowired
    MYSYSService mysysService;

    @GetMapping("/article/{id}")
    public ResultE<String> init(@PathVariable String id){
        return new ResultE<String>().execute(result -> {
            result.setSuccess(mysysService.save(id));
        });
    }

}
