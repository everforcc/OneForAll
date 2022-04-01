package cn.cc.dawn.open.web.data.controller;

import cn.cc.dawn.open.web.data.dto.WebSiteDataDto;
import cn.cc.dawn.open.web.data.dto.WebSiteDto;
import cn.cc.dawn.open.web.data.service.WebSiteDataService;
import cn.cc.dawn.open.web.data.service.WebSiteService;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/open/craw/page")
@RestController
public class WSPageController {

    /**
     * 处理所有查询相关
     */

    @Autowired
    private WebSiteService accountsService;

    @Autowired
    private WebSiteDataService webSiteDataService;

    @GetMapping("/findAll")
    public ResultE<WebSiteDto> findAll(){

        return new ResultE<WebSiteDto>().execute(e ->
                e.setSuccess(accountsService.selectAll())
        );
        //return accountsService.selectAll();
    }

    @GetMapping("/findData/{parentid}")
    public ResultE<WebSiteDataDto> findData(@PathVariable int parentid){

        return new ResultE<WebSiteDataDto>().execute(e ->
                e.setSuccess(webSiteDataService.select(parentid))
        );
        //return accountsService.selectAll();
    }


}
