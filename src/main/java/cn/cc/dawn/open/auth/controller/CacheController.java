package cn.cc.dawn.open.auth.controller;

import cn.cc.dawn.config.init.yml.APPCacheConfiguration;
import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.open.auth.util.CustomUserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/open/authcache")
@RestController
public class CacheController {

    @Autowired
    APPCacheConfiguration appCacheConfiguration;

    @Autowired
    CustomUserBuilder customUserBuilder;

    @GetMapping("/cache")
    public String cache(){
        //ICustomerUser iCustomerUser = appCacheConfiguration.getCustomuser();
        CustomerUserCache iCustomerUser = appCacheConfiguration.getCustomuser();

        //return iCustomerUser.cachekey + " : " + iCustomerUser.expired;
        return iCustomerUser.USER_TOKEN + " : " + iCustomerUser.getExpired().getSeconds();
    }

    @PostMapping("/token")
    public String token(@RequestBody String token){

        return customUserBuilder.tokenToUser(token).toString();
    }

}
