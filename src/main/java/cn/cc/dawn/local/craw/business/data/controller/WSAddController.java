package cn.cc.dawn.local.craw.business.data.controller;

import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import cn.cc.dawn.local.craw.business.data.flow.WebSiteCommonService;
import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteServiceImpl;
import cn.cc.dawn.local.craw.business.data.service.impl.WebSiteTagServiceImpl;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO 此类只允许管理员操作
 */
@RequestMapping("/open/craw/add")
@RestController
@Slf4j
//@RequiredArgsConstructor
public class WSAddController {

    /**
     * 网站主表
     */
    @Autowired
    private WebSiteServiceImpl webSiteService;
    /**
     * 网站tag表
     */
    @Autowired
    private WebSiteTagServiceImpl webSiteTagServiceImpl;

    /**
     * http请求测试
     */
    @Autowired
    WebSiteCommonService webSiteCommonService;


    /**
     * 网站主表
     * @param webSiteDto
     * @return
     */
    @PostMapping("/addroot")
    public ResultE<Object> addroot(@RequestBody WebSiteDto webSiteDto){
        /*return new ResultE<Integer>().execute(e ->{
            e.setSuccess(webSiteService.insert(json));
        });*/

        log.info(webSiteDto.toString());
        return new ResultE<>().call(() ->
                webSiteService.insert(webSiteDto)
        );
    }

    /**
     *  网站tag信息
     * @param json
     * @return
     */
    @PostMapping("/addtag")
    public ResultE<Integer> addtag(@RequestBody String json){
        return new ResultE<Integer>().execute(e ->{
            e.setSuccess(webSiteTagServiceImpl.insert(json));
        });
    }


    /**
     * 网站测试
     * @return
     */
    @GetMapping("/testHttpParam")
    public ResultE<Boolean> testHttpParam(){
        return new ResultE<Boolean>().execute(e ->{
            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setUrl("http://www.dzwx520.com/book_8764/4089173.html");
            httpParamDto.setCharset(CharsetsEnum.GBK);
            e.setSuccess(
                    webSiteCommonService.commonflow(httpParamDto)
            );
        });
    }

}
