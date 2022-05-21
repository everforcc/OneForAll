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

package cn.cc.dawn.common.bddisk.controller;

import cn.cc.dawn.common.bddisk.dto.BDRegisterDto;
import cn.cc.dawn.common.bddisk.service.IBDRegisterService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/open/craw/bdwp")
@RestController
@Slf4j
public class BDRegisterController {

    @Resource
    IBDRegisterService ibdRegisterService;

    @GetMapping("/method_a")
    public String method_a(@RequestParam(value = "expires_in",required = false)String expires_in,
                             @RequestParam(value = "access_token",required = false)String access_token,
                             @RequestParam(value = "session_secret",required = false)String session_secret,
                             @RequestParam(value = "session_key",required = false)String session_key,
                             @RequestParam(value = "scope",required = false)String scope,
                             @RequestParam(value = "code",required = false)String code){
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
        bdRegisterDto.setCode(code);

        return JSONObject.toJSONString(ibdRegisterService.saveRedis_1(bdRegisterDto));
    }

    @GetMapping("/method_a/")
    public String method_a2(@RequestParam(value = "expires_in",required = false)String expires_in,
                         @RequestParam(value = "access_token",required = false)String access_token,
                         @RequestParam(value = "session_secret",required = false)String session_secret,
                         @RequestParam(value = "session_key",required = false)String session_key,
                         @RequestParam(value = "scope",required = false)String scope,
                         @RequestParam(value = "code",required = false)String code){
        log.info("method_a2: expires_in: " + expires_in);
        log.info("method_a2: access_token: " + access_token);
        log.info("method_a2: session_secret: " + session_secret);
        log.info("method_a2: session_key: " + session_key);
        log.info("method_a2: scope: " + scope);
        BDRegisterDto bdRegisterDto = new BDRegisterDto();
        bdRegisterDto.setExpires_in(expires_in);
        bdRegisterDto.setAccess_token(access_token);
        bdRegisterDto.setSession_secret(session_secret);
        bdRegisterDto.setSession_key(session_key);
        bdRegisterDto.setScope(scope);
        bdRegisterDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_1(bdRegisterDto));
    }

    @GetMapping("/method_b")
    public String method_b1(@RequestParam(value = "expires_in",required = false)String expires_in,
                          @RequestParam(value = "access_token",required = false)String access_token,
                          @RequestParam(value = "session_secret",required = false)String session_secret,
                          @RequestParam(value = "session_key",required = false)String session_key,
                          @RequestParam(value = "scope",required = false)String scope,
                          @RequestParam(value = "code",required = false)String code){
        log.info("method_b1: expires_in: " + expires_in);
        log.info("method_b1: access_token: " + access_token);
        log.info("method_b1: session_secret: " + session_secret);
        log.info("method_b1: session_key: " + session_key);
        log.info("method_b1: scope: " + scope);
        BDRegisterDto bdRegisterDto = new BDRegisterDto();
        bdRegisterDto.setExpires_in(expires_in);
        bdRegisterDto.setAccess_token(access_token);
        bdRegisterDto.setSession_secret(session_secret);
        bdRegisterDto.setSession_key(session_key);
        bdRegisterDto.setScope(scope);
        bdRegisterDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_2(bdRegisterDto));
    }

    @GetMapping("/method_b/")
    public String method_b2(@RequestParam(value = "expires_in",required = false)String expires_in,
                         @RequestParam(value = "access_token",required = false)String access_token,
                         @RequestParam(value = "session_secret",required = false)String session_secret,
                         @RequestParam(value = "session_key",required = false)String session_key,
                         @RequestParam(value = "scope",required = false)String scope,
                         @RequestParam(value = "code",required = false)String code){
        log.info("method_b2: expires_in: " + expires_in);
        log.info("method_b2: access_token: " + access_token);
        log.info("method_b2: session_secret: " + session_secret);
        log.info("method_b2: session_key: " + session_key);
        log.info("method_b2: scope: " + scope);
        BDRegisterDto bdRegisterDto = new BDRegisterDto();
        bdRegisterDto.setExpires_in(expires_in);
        bdRegisterDto.setAccess_token(access_token);
        bdRegisterDto.setSession_secret(session_secret);
        bdRegisterDto.setSession_key(session_key);
        bdRegisterDto.setScope(scope);
        bdRegisterDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_2(bdRegisterDto));
    }

}
