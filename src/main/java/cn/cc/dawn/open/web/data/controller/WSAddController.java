package cn.cc.dawn.open.web.data.controller;

import cn.cc.dawn.open.web.data.dto.HttpParamDto;
import cn.cc.dawn.open.web.data.dto.WebSiteDto;
import cn.cc.dawn.open.web.data.flow.WebSiteCommonService;
import cn.cc.dawn.open.web.data.service.WebSiteService;
import cn.cc.dawn.open.web.data.service.WebSiteTagService;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private WebSiteService webSiteService;
    /**
     * 网站tag表
     */
    @Autowired
    private WebSiteTagService webSiteTagService;

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
    @GetMapping("/addroot")
    public ResultE<Object> addroot(WebSiteDto webSiteDto){
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
    @GetMapping("/addtag")
    public ResultE<Integer> addtag(@RequestBody String json){
        return new ResultE<Integer>().execute(e ->{
            e.setSuccess(webSiteTagService.insert(json));
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
