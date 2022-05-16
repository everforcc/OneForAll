package cn.cc.dawn.open.auth.controller;

import cn.cc.dawn.config.cache.AppCache;
import cn.cc.dawn.config.init.yml.APPConfigurationCache;
import cn.cc.dawn.open.auth.util.CustomUserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/open/authcache")
@RestController
public class CacheController {

    @Autowired
    APPConfigurationCache appConfigurationCache;

    @Autowired
    CustomUserBuilder customUserBuilder;

    @GetMapping("/cache")
    public String cache(){
        AppCache customerUser = appConfigurationCache.getCustomerUserToken();
        // 缓存key和缓存时间
        return customerUser.getCachekey() + " : " + customerUser.getExpired().getSeconds();
    }

    @PostMapping("/token")
    public String token(@RequestBody String token){

        return customUserBuilder.tokenToUser(token).toString();
    }

}
