package cn.cc.dawn.common.controller;

import cn.cc.dawn.common.dto.HttpParam;
import cn.cc.dawn.utils.enums.CharsetsEnum;
import cn.cc.dawn.common.flow.WebSiteCommonService;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/common/wscommon")
@RestController
public class WebSiteCommonController {

    @Resource
    WebSiteCommonService webSiteCommonService;


    @GetMapping("/test")
    public ResultE<Boolean> test(){

        return new ResultE<Boolean>().execute(e ->{
            HttpParam httpParam = new HttpParam();
            httpParam.setUrl("http://www.dzwx520.com/book_8764/4089173.html");
            httpParam.setCharset(CharsetsEnum.GBK);
            e.setSuccess(
                    webSiteCommonService.commonflow(httpParam)
            );
        });
    }

}
