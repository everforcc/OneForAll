package cn.cc.dawn.business.ys.controller;

import cn.cc.dawn.business.ys.service.YsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/b/yscard")
@RestController
public class YsCardController {

    @Autowired
    YsCardService ysCardService;

    @GetMapping("/init/{type}")
    public int init(@PathVariable String type){
        try {
            return ysCardService.flow(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @GetMapping("/analyse/{uid}")
    public int analyse(@PathVariable String uid){
        try {
            ysCardService.analyse(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
