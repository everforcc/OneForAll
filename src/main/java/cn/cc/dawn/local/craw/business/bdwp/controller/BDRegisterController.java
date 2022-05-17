/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 15:06
 * Copyright
 */

package cn.cc.dawn.local.craw.business.bdwp.controller;

import cn.cc.dawn.local.craw.business.bdwp.dto.BDRegisterDto;
import cn.cc.dawn.local.craw.business.bdwp.service.IBDRegisterService;
import cn.cc.dawn.local.craw.business.data.vo.WebSiteReqVO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/open/craw/bdwp")
@RestController
@Slf4j
public class BDRegisterController {

    @Resource
    IBDRegisterService ibdRegisterService;

    @GetMapping("/method_a")
    public void method_a(@RequestParam(value = "expires_in",required = false)String expires_in,
                             @RequestParam(value = "access_token",required = false)String access_token,
                             @RequestParam(value = "session_secret",required = false)String session_secret,
                             @RequestParam(value = "session_key",required = false)String session_key,
                             @RequestParam(value = "scope",required = false)String scope){
        log.info("a: expires_in: " + expires_in);
        log.info("a: access_token: " + access_token);
        log.info("a: session_secret: " + session_secret);
        log.info("a: session_key: " + session_key);
        log.info("a: scope: " + scope);
        BDRegisterDto bdRegisterDto = new BDRegisterDto();
        bdRegisterDto.setExpires_in(expires_in);
        bdRegisterDto.setAccess_token(access_token);
        bdRegisterDto.setSession_secret(session_secret);
        bdRegisterDto.setSession_key(session_key);
        bdRegisterDto.setScope(scope);
        ibdRegisterService.saveRedis_1(bdRegisterDto);
    }

    @GetMapping("/method_b")
    public void method_b(@RequestParam(value = "expires_in",required = false)String expires_in,
                         @RequestParam(value = "access_token",required = false)String access_token,
                         @RequestParam(value = "session_secret",required = false)String session_secret,
                         @RequestParam(value = "session_key",required = false)String session_key,
                         @RequestParam(value = "scope",required = false)String scope){
        log.info("b: expires_in: " + expires_in);
        log.info("b: access_token: " + access_token);
        log.info("b: session_secret: " + session_secret);
        log.info("b: session_key: " + session_key);
        log.info("b: scope: " + scope);
        BDRegisterDto bdRegisterDto = new BDRegisterDto();
        bdRegisterDto.setExpires_in(expires_in);
        bdRegisterDto.setAccess_token(access_token);
        bdRegisterDto.setSession_secret(session_secret);
        bdRegisterDto.setSession_key(session_key);
        bdRegisterDto.setScope(scope);
        ibdRegisterService.saveRedis_2(bdRegisterDto);
    }

}
