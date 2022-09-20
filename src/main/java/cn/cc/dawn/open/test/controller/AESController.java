package cn.cc.dawn.open.test.controller;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.config.init.yml.APPConfigurationEncrypt;
import cn.cc.dawn.utils.commons.codec.JAESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/open/aes")
@RestController
public class AESController {

    @Autowired
    APPConfigurationEncrypt appConfigurationEncrypt;

    @GetMapping("/t")
    public String aes(){

        String strKey = "aesKey";
        String json = "{\"phone\":\"13813813138\",\"lon\":\"120.6794\",\"lat\":\"31.31696\"}";
        log.info("原始json: " + json);
        String str = JAESUtil.aes_encrypt(json, strKey);
        log.info("加密后str: " + str);
        String dec_json = JAESUtil.aes_decrypt(str, strKey);
        log.info("解密后json: " + dec_json);

        log.info("-----");
        String default_str = JAESUtil.aes_encrypt(json,appConfigurationEncrypt.getDefaultAeskey());
        log.info("加密后str: 使用yml的key " + default_str);

        return dec_json;
    }

}
