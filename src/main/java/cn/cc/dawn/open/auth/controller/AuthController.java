package cn.cc.dawn.open.auth.controller;

import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.open.auth.service.AuthService;
import cn.cc.dawn.open.auth.vo.CustomUserLoginVO;
import cn.cc.dawn.utils.entity.ResultE;
import cn.cc.dawn.utils.exception.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/open/auth")
@RestController
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * 用户名密码登录
     */
    @PostMapping("/login")
    public ResultE<String> login(@RequestBody CustomUserLoginVO customUserLoginVO){
        return new ResultE<String>().execute(result -> {
            result.setSuccess(authService.authToken(customUserLoginVO.getUsername(),customUserLoginVO));
        });
    }

    @PostMapping("/check")
    public ResultE<String> check(@RequestBody CustomUser customUser){
        return new ResultE<String>().execute(result -> {
            result.setSuccess(authService.check(customUser));
        });
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public ResultE<String> logout(){
        log.info("清除用户token");
        return new ResultE<String>(Code.A00000);
    }

}
