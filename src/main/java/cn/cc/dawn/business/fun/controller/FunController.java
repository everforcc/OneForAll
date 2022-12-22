package cn.cc.dawn.business.fun.controller;

import cn.cc.dawn.demo.init.txt.service.InitService;
import cn.cc.dawn.local.craw.business.bilibili.flow.Bilibili_Cover;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/open/fun")
public class FunController {

    @Autowired
    InitService initService;

    @GetMapping("/getfun")
    public String getfun() {
        return initService.init();
    }

    /**
     * controller
     * 管理链接，和前端对接
     */

    Bilibili_Cover bilibili_cover = new Bilibili_Cover();

    @PostMapping("/bilibili_cover")
    public ResultE<String> getCover(@RequestBody String json) {
        log.info("json: {}", json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return new ResultE<String>().execute(e ->
                e.setSuccess(bilibili_cover.getBilCover(jsonObject.getString("num")))
        );
    }

}
