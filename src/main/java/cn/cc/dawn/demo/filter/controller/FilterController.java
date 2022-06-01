package cn.cc.dawn.demo.filter.controller;

import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试过滤器
 */
@RequestMapping("/demo/filter")
@RestController
public class FilterController {

    @GetMapping("/t")
    public ResultE<String> tfilter(){
        return new ResultE<String>().setSuccess("从filter返回");
    }

}
