package cn.cc.dawn.business.ys.controller;

import cn.cc.dawn.business.ys.service.YsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/yscard")
@RestController
public class YsCardController {

    @Autowired
    YsCardService ysCardService;

    @GetMapping("/init")
    public int init(){
        try {
            return ysCardService.flow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
