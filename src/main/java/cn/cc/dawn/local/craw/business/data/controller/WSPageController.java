package cn.cc.dawn.local.craw.business.data.controller;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteDataServiceImpl;
import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteServiceImpl;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/open/craw/page")
@RestController
public class WSPageController {

    /**
     * 处理所有查询相关
     */

    @Autowired
    private WebSiteServiceImpl accountsService;

    @Autowired
    private WebSiteDataServiceImpl webSiteDataServiceImpl;

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
                e.setSuccess(webSiteDataServiceImpl.select(parentid))
        );
        //return accountsService.selectAll();
    }


}
