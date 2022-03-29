package cn.cc.dawn.open.test;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.utils.algo.AESUtil;
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
    APPConfiguration appConfiguration;

    @GetMapping("/t")
    public String aes(){
        String json = "{\"phone\":\"13813813138\",\"lon\":\"120.6794\",\"lat\":\"31.31696\"}";
        log.info("原始json: " + json);
        String str = AESUtil.aes_encrypt(json);
        log.info("加密后str: " + str);
        String dec_json = AESUtil.aes_decrypt(str);
        log.info("解密后json: " + dec_json);

        log.info("-----");
        String default_str = AESUtil.aes_encrypt(json,appConfiguration.getDefaultaeskey());
        log.info("加密后str: 使用yml的key " + default_str);

        return dec_json;
    }

}
