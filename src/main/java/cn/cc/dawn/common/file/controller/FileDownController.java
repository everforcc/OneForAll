package cn.cc.dawn.common.file.controller;

import cn.cc.dawn.common.file.service.IServletService;
import cn.cc.dawn.common.file.service.ResponseBytesService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/open/file/read")
public class FileDownController {

    @Autowired
    private ResponseBytesService responseBytesService;

    @Autowired
    IServletService iServletService;

    /**
     * 读取文件的字节流返回
     */
    @GetMapping("/down/{uuname}")
    public void down(HttpServletResponse response, @PathVariable String uuname){
        new ResultE<Void>().call(()->{
            iServletService.down(response,uuname);
        });

    }

    @GetMapping("/returnTXT")
    @ResponseBody
    public void returnTXT(HttpServletResponse response) throws Exception {
        new ResultE<Void>().call(()->{
            iServletService.read(response,"");
        });
    }


}
