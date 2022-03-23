package cn.cc.dawn.demo.httpclient.controller;

import cn.cc.dawn.demo.craw.website.dto.MailDto;
import cn.cc.dawn.demo.httpclient.service.HttpClientService;
import cn.cc.dawn.config.component.MailFreemarkerComponent;
import cn.cc.dawn.config.component.MailThymeleafComponent;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/httpclient")
@RestController
public class HttpClientController {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    MailThymeleafComponent mailTemplateEngine;
    @Autowired
    MailFreemarkerComponent mailFreemarkerComponent;


    @GetMapping("/feign_post/{uid}")
    public JSONObject feignClient(@PathVariable("uid")String uid){
        return httpClientService.feignPost_AlbumCount(uid);
    }

    @PostMapping("/MailThymeleaf")
    public void MailThymeleaf(@RequestBody String json){
        MailDto mailDto = new MailDto();
        try {
            mailTemplateEngine.send(JSONObject.parseObject(json,MailDto.class));
            List<String> stringList = new ArrayList<>();
            stringList.add("aaa");
            mailDto.setAttachmentURL(stringList);
            mailDto.setFrom("abcabc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return JSONObject.toJSONString(mailDto);
    }

//    @GetMapping("/MailFreemarker")
//    public void MailFreemarker(){
//        try {
//            mailFreemarkerComponent.sendFreemarkerMail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
