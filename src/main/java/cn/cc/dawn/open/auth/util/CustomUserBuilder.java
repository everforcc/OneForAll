package cn.cc.dawn.open.auth.util;

import cn.cc.dawn.config.init.application.ApplicationContextInit;
import cn.cc.dawn.open.auth.cache.ICustomerUser;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.algo.AESUtil;
import cn.cc.dawn.utils.algo.UUIDUtils;
import cn.cc.dawn.utils.check.ObjectUtils;
import cn.cc.dawn.utils.exception.AppCode;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(-1) // 最高优先级
public class CustomUserBuilder {

    /*private static ApplicationContext applicationContext;

    @Bean
    public CommandLineRunner beanInitPrintCommandLineRunner(ApplicationContext context) {
        applicationContext = context;
        return args -> {

        };
    }*/

//    @Autowired
//    RedisBoundValueOperationsUtils redis;

    public static String token(CustomUser customUser){
        // 生成token
        /**
         * 设置个随机数，保证每次生成不同
         */
        customUser.setUuid(UUIDUtils.uuid32());
        String json = JSONObject.toJSONString(customUser);
        return AESUtil.aes_encrypt(json);
    }

    /**
     * 正式使用改为加密数据，学习时方便测试
     * @param json
     * @return
     */
    public static CustomUser tokenToUser(String json){

        log.info("解密前json: " + json);
        json = AESUtil.aes_decrypt(json);
        log.info("解密后json: " + json);
        CustomUser customUser = JSONObject.parseObject(json,CustomUser.class);
        // 拿到user取数据库查询
        // 在redis 检查是否存在，如果不存在
        AppCode.A00102.assertHasTrue(ObjectUtils.nonNull(customUser));

        /**
         * 在初始化之前获取 CacheManager
         */
        CacheManager cacheManager = ApplicationContextInit.getBean(CacheManager.class);

        Cache cache = cacheManager.getCache(ICustomerUser.USER_TOKEN);

        String token = cache.get(customUser.getUsername(),String.class);

        log.info("解密前token: " + token);
        token = AESUtil.aes_decrypt(token);
        log.info("解密后token: " + token);

        CustomUser customUserCache = JSONObject.parseObject(token,CustomUser.class);
        //AppCode.A01004.assertHasTrue(redis.hasKeyCacheAble(ICustomerUser.USER_TOKEN,customUser.getUsername()));
        AppCode.A00102.assertHasTrue( customUserCache.getUuid().equals(customUser.getUuid()) );
        return customUser;
    }

    public static void removeToken(String json){
        log.info("解密前json: " + json);
        json = AESUtil.aes_decrypt(json);
        log.info("解密后json: " + json);
        CustomUser customUser = JSONObject.parseObject(json,CustomUser.class);
        // 拿到user取数据库查询
        // 在redis 检查是否存在，如果不存在
        AppCode.A00102.assertHasTrue(ObjectUtils.nonNull(customUser));

        /**
         * 在初始化之前获取 CacheManager
         */
        CacheManager cacheManager = ApplicationContextInit.getBean(CacheManager.class);

        Cache cache = cacheManager.getCache(ICustomerUser.USER_TOKEN);
        cache.clear();
    }


}
