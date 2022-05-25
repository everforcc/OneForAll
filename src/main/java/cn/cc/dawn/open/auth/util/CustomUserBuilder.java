package cn.cc.dawn.open.auth.util;

import cn.cc.dawn.config.init.application.ApplicationContextInit;
import cn.cc.dawn.open.auth.cache.CustomerUserCache;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.commons.codec.JAESUtil;
import cn.cc.dawn.utils.commons.codec.JUUIDUtils;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
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

//    @Autowired
//    RedisBoundValueOperationsUtils redis;

    public static String token(CustomUser customUser){
        // 生成token
        /**
         * 设置个随机数，保证每次生成不同
         */
        customUser.setUuid(JUUIDUtils.uuid32());
        String json = JSONObject.toJSONString(customUser);
        return JAESUtil.aes_encrypt(json);
    }

    /**
     * 正式使用改为加密数据，学习时方便测试
     * @param json
     * @return
     */
    public static CustomUser tokenToUser(String json){

        log.info("解密前json: " + json);
        json = JAESUtil.aes_decrypt(json);
        log.info("解密后json: " + json);
        CustomUser customUser = JSONObject.parseObject(json,CustomUser.class);
        // 拿到user取数据库查询
        // 在redis 检查是否存在，如果不存在
        AppCode.A00102.assertHasTrue(RObjectsUtils.nonNull(customUser));

        /**
         * 在初始化之前获取 CacheManager
         */
        CacheManager cacheManager = ApplicationContextInit.getBean(CacheManager.class);

        Cache cache = cacheManager.getCache(CustomerUserCache.CUSTOMERUSER_TOKEN);

        String token = cache.get(customUser.getUsername(),String.class);

        AppCode.A00102.assertNonNull(token);

        log.info("解密前token: " + token);
        token = JAESUtil.aes_decrypt(token);
        log.info("解密后token: " + token);

        CustomUser customUserCache = JSONObject.parseObject(token,CustomUser.class);
        //AppCode.A01004.assertHasTrue(redis.hasKeyCacheAble(ICustomerUser.USER_TOKEN,customUser.getUsername()));
        AppCode.A00102.assertHasTrue( customUserCache.getUuid().equals(customUser.getUuid()) );
        return customUser;
    }

    public static void removeToken(String json){
        log.info("解密前json: " + json);
        json = JAESUtil.aes_decrypt(json);
        log.info("解密后json: " + json);
        CustomUser customUser = JSONObject.parseObject(json,CustomUser.class);
        // 拿到user取数据库查询
        // 在redis 检查是否存在，如果不存在
        AppCode.A00102.assertHasTrue(RObjectsUtils.nonNull(customUser));

        /**
         * 在初始化之前获取 CacheManager
         */
        CacheManager cacheManager = ApplicationContextInit.getBean(CacheManager.class);

        Cache cache = cacheManager.getCache(CustomerUserCache.CUSTOMERUSER_TOKEN);
        cache.clear();
    }


}
