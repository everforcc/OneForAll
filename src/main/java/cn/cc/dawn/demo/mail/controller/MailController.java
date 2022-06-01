package cn.cc.dawn.demo.mail.controller;

import cn.cc.dawn.demo.mail.dto.MailDto;
import cn.cc.dawn.demo.mail.service.IMailService;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/demo/mail")
@RestController
public class MailController {

    @Autowired
    IMailService mailServiceThymeleafImpl;
    @Autowired
    IMailService mailServiceFreemarkerImpl;

    /**
     * 发送工资模板
     * @param json
     * @return
     */
    @PostMapping("/testMailThymeleaf")
    public ResultE<Void> testMailThymeleaf(@RequestBody String json){
        return new ResultE<Void>().call(() ->{
            mailServiceThymeleafImpl.send(JSONObject.parseObject(json,MailDto.class));
        });
    }

    @PostMapping("/testMailFreemarker")
    public ResultE<Void> MailFreemarker(@RequestBody String json){
        return new ResultE<Void>().call(() ->{
            mailServiceFreemarkerImpl.send(JSONObject.parseObject(json,MailDto.class));
        });
    }

}
