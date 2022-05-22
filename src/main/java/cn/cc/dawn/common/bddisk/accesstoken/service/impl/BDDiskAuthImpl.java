/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 15:43
 * Copyright
 */

package cn.cc.dawn.common.bddisk.accesstoken.service.impl;

import cn.cc.dawn.common.bddisk.constant.BDDiskConstant;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskAuthDto;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskRefreshTokenDto;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenReqDto;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskAuthService;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskTokenResultDtoService;
import cn.cc.dawn.config.cache.CacheConstant;
import cn.cc.dawn.config.cache.CacheUserDefine;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BDDiskAuthImpl implements IBDDiskAuthService {

    @Autowired
    APPConfigurationBDDisk appConfigurationBDDisk;
    @Autowired
    IHttp httpApacheImpl;
//    @Autowired
//    IRedisTemplate redisBoundValueOperationsUtils;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    IBDDiskTokenResultDtoService ibdDiskTokenResultDtoService;
    /**
     * 基本上就是固定数值
     * @return
     */
    @Override
    public String getUserAuthUrl() {
        log.info("获取引导用户授权地址");
        String redirect_uriWebUrl = appConfigurationBDDisk.getRedirect_uriWebUrl();
        String client_id = appConfigurationBDDisk.getAPI_Key();

        final RBucket<String> bdDiskOrderUserAuthRBucket = redissonClient.getBucket(CacheUserDefine.BD_DISK_ORDERUSESR_AUTH.formatKey(client_id,redirect_uriWebUrl));
        String orderUserAuthUrl = bdDiskOrderUserAuthRBucket.get();
        if(StringUtils.isBlank(orderUserAuthUrl)) {
            log.info("从缓存中获取地址: ");
            String authorizeWebUrl = appConfigurationBDDisk.getAuthorizeWebUrl();
            BDDiskAuthDto bdDiskAuthDto = new BDDiskAuthDto(client_id, redirect_uriWebUrl);
            orderUserAuthUrl = bdDiskAuthDto.toStringWithBaseUrl(authorizeWebUrl);
            bdDiskOrderUserAuthRBucket.set(orderUserAuthUrl, CacheConstant.defaultCacheDay, TimeUnit.DAYS);
        }
        log.info("引导用户授权地址: {}", orderUserAuthUrl);
        return orderUserAuthUrl;
    }

    /**
     * 这个code应该是和用户对应的，上传新的覆盖旧的
     * 根据用户uuid保存到redis
     * 并入库，如果数据库存在旧的覆盖掉
     * 用户 -> code -> AccessToken
     * @param code
     * @return
     */
    @Override
    public String getAccessToken(int userid, String code, boolean coverOldToken) {
        String client_id = appConfigurationBDDisk.getAPI_Key();
        String secret_Key = appConfigurationBDDisk.getSecret_Key();
        String redirect_uriWebUrl = appConfigurationBDDisk.getRedirect_uriWebUrl();
        String access_tokenWebUrl = appConfigurationBDDisk.getAccess_tokenWebUrl();

        final RBucket<String> bdDiskTokenResultDtoRBucket = redissonClient.getBucket(CacheUserDefine.BD_DISK_ACCESS_TOKEN.formatKey(String.valueOf(userid)));
        String bdAccessToken = null;
        /**
         * 从redis取出来
         * 如果true就表示强制覆盖掉，不再从redis取数据
         */
        if(!coverOldToken) {
            bdAccessToken = bdDiskTokenResultDtoRBucket.get();
        }

        if(StringUtils.isBlank(bdAccessToken)) {

            BDDiskTokenReqDto bdDiskTokenReqDto = new BDDiskTokenReqDto(client_id, secret_Key, redirect_uriWebUrl);
            // 请求数据的code
            bdDiskTokenReqDto.setCode(code);
            // 组装参数获取请求url
            String redirect_uriWebUrlWithParams = bdDiskTokenReqDto.toStringWithBaseUrl(access_tokenWebUrl);

            // 请求数据获得json
            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setUrl(redirect_uriWebUrlWithParams);
            String resultJson = httpApacheImpl.getMsg(httpParamDto);

            //String resultJson = "{\"expires_in\":2592000,\"refresh_token\":\"122.69ba09269f6b5d7d50836be337a033f8.YGKwCs2e8T3AECCs4vGXeVcnUjhDKaXU_IaQjnw.Aas7Og\",\"access_token\":\"121.0fb83417d70c997200958befd51b8a4a.Y74bioNT1ki9PUQ1cLpW3fE5_3SdYyGCoHIbL3-.3c4HvQ\",\"session_secret\":\"\",\"session_key\":\"\",\"scope\":\"basic netdisk\"}";
            //String errorJson = {"error":"invalid_grant","error_description":"invalid code , expired or revoked"};
            log.info("code换token返回数据: {}",resultJson);

            BDDiskTokenResultDto bdDiskTokenResultDto = JSONObject.parseObject(resultJson, BDDiskTokenResultDto.class);
            // 请求时的code
            bdDiskTokenResultDto.setCode(code);

            // 对返回数据进行校验
            AppCode.A01400.assertHasTrue(StringUtils.isBlank(bdDiskTokenResultDto.getError()),bdDiskTokenResultDto.getError_description());

            // 入库
            ibdDiskTokenResultDtoService.insert(bdDiskTokenResultDto,userid);
            // 放入redis
            bdAccessToken = bdDiskTokenResultDto.getAccess_token();
            bdDiskTokenResultDtoRBucket.set(bdAccessToken,bdDiskTokenResultDto.getExpires_in() - BDDiskConstant.holdTime, TimeUnit.SECONDS);

            log.info("请求链接, {}", redirect_uriWebUrlWithParams);
            log.info("返回json, {}", resultJson);
        }else {
            log.info("从缓存中取出数据, {}",bdAccessToken);
        }

        return bdAccessToken;
    }

    /**
     * 主动刷新token
     * @param refresh_token
     * @return
     */
    @Override
    public BDDiskTokenResultDto refreshAccessToken(int userid, String refresh_token) {
        String client_id = appConfigurationBDDisk.getAPI_Key();
        String secret_Key = appConfigurationBDDisk.getSecret_Key();
        String refresh_tokenWebUrl = appConfigurationBDDisk.getRefresh_tokenWebUrl();

        BDDiskRefreshTokenDto bdDiskRefreshTokenDto = new BDDiskRefreshTokenDto(client_id,secret_Key);
        bdDiskRefreshTokenDto.setRefresh_token(refresh_token);
        String refresh_tokenWebUrlParams = bdDiskRefreshTokenDto.toStringWithBaseUrl(refresh_tokenWebUrl);

        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(refresh_tokenWebUrlParams);
        String resultJson = httpApacheImpl.getMsg(httpParamDto);

        log.info("刷新返回json: {}", resultJson);
        final RBucket<String> bdDiskTokenResultDtoRBucket = redissonClient.getBucket(CacheUserDefine.BD_DISK_ACCESS_TOKEN.formatKey(String.valueOf(userid)));

        BDDiskTokenResultDto bdDiskTokenResultDto = JSONObject.parseObject(resultJson, BDDiskTokenResultDto.class);
        String access_token = bdDiskTokenResultDto.getAccess_token();
        /**
         * 放入redis
         */
        bdDiskTokenResultDtoRBucket.set(access_token,bdDiskTokenResultDto.getExpires_in() - BDDiskConstant.holdTime, TimeUnit.SECONDS);

        log.info("刷新成功: {}", bdDiskTokenResultDto);

        return bdDiskTokenResultDto;
    }

}
