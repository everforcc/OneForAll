/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 18:07
 */

package cn.cc.dawn.common.bddisk.accesstoken.serv.impl;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskAuthApi;
import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDDiskTokenResultDtoService;
import cn.cc.dawn.config.cache.CacheUserDefine;
import cn.cc.dawn.utils.commons.lang.RStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 合并流程流程,
 * 提供给其他接口使用
 */
@Slf4j
@Service
public class BDDiskOfferAuthServiceImpl implements IBDDiskOfferAuthService {

    @Resource
    IBDDiskAuthApi ibdDiskAuthApi;
    @Resource
    RedissonClient redissonClient;
    @Resource
    IBDDiskTokenResultDtoService ibdDiskTokenResultDtoService;

    /**
     * 1. 根据userid从缓存取
     * 2. 取不出来查数据库，拿到refere
     * 3. 刷新accessToken放入缓存
     * 4. 异常返回给前端处理，重新获取
     *
     * @param userid 用户id
     * @return token
     */
    @Override
    public String offerAccessToken(int userid) {
        final RBucket<String> bdDiskTokenResultDtoRBucket = redissonClient.getBucket(CacheUserDefine.BD_DISK_ACCESS_TOKEN.formatKey(String.valueOf(userid)));
        String bdAccessToken = bdDiskTokenResultDtoRBucket.get();

        /* 如果为空,查数据库 */
        if (RStringUtils.isBlank(bdAccessToken)) {
            log.info("token失效,主动刷新");
            BDDiskTokenResultDto bdDiskTokenResultDto = ibdDiskTokenResultDtoService.select(userid);
            log.info("旧数据: {}", bdDiskTokenResultDto);
            String refresh_token = bdDiskTokenResultDto.getRefresh_token();
            BDDiskTokenResultDto refereResult = ibdDiskAuthApi.refreshAccessToken(userid, refresh_token);
            bdDiskTokenResultDto.setAccess_token(refereResult.getAccess_token());
            bdDiskTokenResultDto.setRefresh_token(refereResult.getRefresh_token());
            /* 刷新过之后，更新数据库 */
            log.info("新数据: {}", bdDiskTokenResultDto);
            ibdDiskTokenResultDtoService.insert(bdDiskTokenResultDto, userid);
            bdAccessToken = bdDiskTokenResultDto.getAccess_token();
        } else {
            log.info("token还存在: {}", bdAccessToken);
        }

        return bdAccessToken;
    }
}
