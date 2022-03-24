package cn.cc.dawn.open.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Yukino
 * 2020/6/21
 */
@Controller
public class ViewController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "business/fun";
        //return "index";
    }

}
