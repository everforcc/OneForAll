package cn.cc.dawn.local.craw.business.sp4u.controller;

import cn.cc.dawn.local.craw.business.sp4u.service.Sp4uService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/local/crawsp4u")
@RestController
public class Sp4uController {

    @Autowired
    Sp4uService sp4uService;

    @GetMapping("/add")
    public int add(){
        String url = "http://spring4u.info/forumdisplay.php?fid=124";
        return sp4uService.flow(url);
    }

}
