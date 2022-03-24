package cn.cc.dawn.demo.craw.webcommon.controller;

import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDto;
import cn.cc.dawn.demo.craw.webcommon.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/d/craw/page")
@RestController
public class WSPageController {

    /**
     * 处理所有查询相关
     */

    @Autowired
    private WebSiteService accountsService;

    @GetMapping("/findAll")
    public List<WebSiteDto> findAll(){
        return accountsService.selectAll();
    }

}
