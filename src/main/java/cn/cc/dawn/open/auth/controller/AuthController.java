package cn.cc.dawn.open.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/open/auth")
@RestController
public class AuthController {

    /**
     * 用户名密码登录
     */

    @PostMapping("/login")
    public void login(@RequestBody String body){

    }


}
