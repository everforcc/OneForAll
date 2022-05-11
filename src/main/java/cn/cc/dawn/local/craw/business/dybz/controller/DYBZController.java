package cn.cc.dawn.local.craw.business.dybz.controller;

import cn.cc.dawn.local.craw.business.dybz.service.DYBZService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/local/novel/dybz")
@RestController
@Slf4j
public class DYBZController {

    @Autowired
    DYBZService dybzService;

    @GetMapping("/tpool")
    public void tPool(){
        dybzService.tPool();
    }

    @GetMapping("/tpool2")
    public void tPool2(){
        dybzService.tPool2();
    }

}
