package cn.cc.dawn.demo.craw.webcommon.controller;

import cn.cc.dawn.demo.craw.webcommon.service.WebSiteService;
import cn.cc.dawn.demo.craw.webcommon.service.WebSiteTagService;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/d/craw/add")
@RestController
//@RequiredArgsConstructor
public class WSAddController {

    @Autowired
    private WebSiteService accountsService;
    @Autowired
    private WebSiteTagService webSiteTagService;


    /**
     * 网站主表
     * @param json
     * @return
     */
    @GetMapping("/addroot")
    public ResultE<Integer> addmain(@RequestBody String json){
        return new ResultE<Integer>().execute(e ->{
            e.setSuccess(accountsService.insert(json));
        });
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

}
