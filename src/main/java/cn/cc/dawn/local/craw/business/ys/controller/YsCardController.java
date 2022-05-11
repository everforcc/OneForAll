package cn.cc.dawn.local.craw.business.ys.controller;

import cn.cc.dawn.local.craw.business.ys.service.YsCardService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/local/yscard")
@RestController
@Slf4j
public class YsCardController {

    @Autowired
    YsCardService ysCardServiceImpl;

    @PostMapping("/userHTMLUrl")
    public ResultE<Integer> init(@RequestBody String json){
        return new ResultE<Integer>().execute(result -> {
            result.setSuccess(ysCardServiceImpl.htmlUrlFlow(json));
        });
    }

    @PostMapping("/userJsonUrl")
    public ResultE<Integer> userUrl(@RequestBody String json){
        return new ResultE<Integer>().execute(result -> {
            result.setSuccess(ysCardServiceImpl.jsonUrlFlow(json));
        });
    }

    @PostMapping("/analyse")
    public ResultE<Map> analyse(@RequestBody String json){
        return new ResultE<Map>().execute(result -> {
            result.setSuccess(ysCardServiceImpl.analyse(json));
        });
    }

}
