package cn.cc.dawn.common.controller;

import cn.cc.dawn.common.dto.WebSiteDto;
import cn.cc.dawn.common.dto.WebSiteTagDto;
import cn.cc.dawn.common.service.WebSiteService;
import cn.cc.dawn.common.service.WebSiteTagService;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/common/accounts")
@RestController
//@RequiredArgsConstructor
public class WebSiteController {

    @Autowired
    private WebSiteService accountsService;
    @Autowired
    private WebSiteTagService webSiteTagService;

    @GetMapping("/findAll")
    public List<WebSiteDto> findAll(){
        return accountsService.selectAll();
    }

    @GetMapping("/addmain")
    public ResultE<Integer> addmain(@RequestBody String json){
        return new ResultE<Integer>().execute(e ->{
            e.setSuccess(accountsService.insert(json));
        });
    }

    @GetMapping("/addtag")
    public ResultE<Integer> addtag(@RequestBody String json){
        return new ResultE<Integer>().execute(e ->{
            e.setSuccess(webSiteTagService.insert(json));
        });
    }

}
