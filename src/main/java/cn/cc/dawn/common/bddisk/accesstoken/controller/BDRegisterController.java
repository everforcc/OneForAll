package cn.cc.dawn.common.bddisk.accesstoken.controller;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDRegisterCallBackDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDRegisterService;
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
        BDRegisterCallBackDto bdRegisterCallBackDto = new BDRegisterCallBackDto();
        bdRegisterCallBackDto.setExpires_in(expires_in);
        bdRegisterCallBackDto.setAccess_token(access_token);
        bdRegisterCallBackDto.setSession_secret(session_secret);
        bdRegisterCallBackDto.setSession_key(session_key);
        bdRegisterCallBackDto.setScope(scope);
        bdRegisterCallBackDto.setCode(code);

        return JSONObject.toJSONString(ibdRegisterService.saveRedis_1(bdRegisterCallBackDto));
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
        BDRegisterCallBackDto bdRegisterCallBackDto = new BDRegisterCallBackDto();
        bdRegisterCallBackDto.setExpires_in(expires_in);
        bdRegisterCallBackDto.setAccess_token(access_token);
        bdRegisterCallBackDto.setSession_secret(session_secret);
        bdRegisterCallBackDto.setSession_key(session_key);
        bdRegisterCallBackDto.setScope(scope);
        bdRegisterCallBackDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_1(bdRegisterCallBackDto));
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
        BDRegisterCallBackDto bdRegisterCallBackDto = new BDRegisterCallBackDto();
        bdRegisterCallBackDto.setExpires_in(expires_in);
        bdRegisterCallBackDto.setAccess_token(access_token);
        bdRegisterCallBackDto.setSession_secret(session_secret);
        bdRegisterCallBackDto.setSession_key(session_key);
        bdRegisterCallBackDto.setScope(scope);
        bdRegisterCallBackDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_2(bdRegisterCallBackDto));
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
        BDRegisterCallBackDto bdRegisterCallBackDto = new BDRegisterCallBackDto();
        bdRegisterCallBackDto.setExpires_in(expires_in);
        bdRegisterCallBackDto.setAccess_token(access_token);
        bdRegisterCallBackDto.setSession_secret(session_secret);
        bdRegisterCallBackDto.setSession_key(session_key);
        bdRegisterCallBackDto.setScope(scope);
        bdRegisterCallBackDto.setCode(code);
        return JSONObject.toJSONString(ibdRegisterService.saveRedis_2(bdRegisterCallBackDto));
    }

}
