package cn.cc.dawn.controller;

import cn.cc.dawn.jdkcraw.Bilibili_Cover;
import cn.cc.dawn.utils.Init;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Yukino
 * 2020/6/22
 */
@RestController
public class MsgRestController {

    /**
     *  controller
     *  管理链接，和前端对接
     */

    Bilibili_Cover bilibili_cover = new Bilibili_Cover();
    Init init =  Init.getInstanse();

    @RequestMapping("/getfun")
    public String getfun(){
        return init.getFun();
    }


    @RequestMapping("/bilibili_cover")
    public String getCover(@RequestParam("num") String num ){

        System.out.println(num);
        return bilibili_cover.getBilCover(num);
    }

}
