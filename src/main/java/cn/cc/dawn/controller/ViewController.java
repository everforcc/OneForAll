package cn.cc.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Yukino
 * 2020/6/21
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String index(){
        return "fun ";
        //return "index";
    }
}
