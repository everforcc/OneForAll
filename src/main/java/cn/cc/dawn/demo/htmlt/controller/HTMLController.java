package cn.cc.dawn.demo.htmlt.controller;

import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/demo/htmlController")
@RestController
@Slf4j
public class HTMLController {


    @GetMapping("/rest/{pm}")
    public String getRest(@PathVariable String pm){
        log.info(pm);
        return pm;
    }

    @GetMapping("/params")
    public ResultE<String> getParams(@RequestParam("pm") String pm){
        log.info(pm);
        return new ResultE<String>().execute(e ->{
            e.setSuccess("json");
        });
    }

}
