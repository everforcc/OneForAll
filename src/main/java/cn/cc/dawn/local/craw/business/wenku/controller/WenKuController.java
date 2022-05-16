package cn.cc.dawn.local.craw.business.wenku.controller;

import cn.cc.dawn.local.craw.business.data.vo.WebSiteReqVO;
import cn.cc.dawn.local.craw.business.novel.service.INovelServiceBusi;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/open/craw/wenku")
@RestController
@Slf4j
public class WenKuController {

    @Resource(name = "novelServiceWenKuImpl")
    INovelServiceBusi iNovelServiceBusi;

    @PostMapping("/content")
    public void novelContent(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        WebSiteReqVO webSiteReqVO = JSONObject.parseObject(json, WebSiteReqVO.class);
        iNovelServiceBusi.novelContent(webSiteReqVO);
    }

}
