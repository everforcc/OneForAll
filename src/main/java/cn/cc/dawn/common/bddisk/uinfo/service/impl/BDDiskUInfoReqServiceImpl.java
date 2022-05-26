/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-22 10:51
 * Copyright
 */

package cn.cc.dawn.common.bddisk.uinfo.service.impl;

import cn.cc.dawn.common.bddisk.accesstoken.service.IBDDiskOfferAuthService;
import cn.cc.dawn.common.bddisk.constant.BDDiskErrorCode;
import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoReqDto;
import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;
import cn.cc.dawn.common.bddisk.uinfo.service.IBDDiskUInfoReqService;
import cn.cc.dawn.common.bddisk.uinfo.service.IBDDiskUInfoResService;
import cn.cc.dawn.config.cache.CacheUserDefine;
import cn.cc.dawn.config.init.yml.APPConfigurationBDDisk;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.IHttp;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class BDDiskUInfoReqServiceImpl implements IBDDiskUInfoReqService {

    @Autowired
    IBDDiskOfferAuthService ibdDiskOfferAuthService;
    @Resource
    APPConfigurationBDDisk appConfigurationBDDisk;
    @Autowired
    IHttp httpApacheImpl;
    @Autowired
    RedissonClient redissonClient;
    @Resource
    IBDDiskUInfoResService ibdDiskUInfoResServicel;

    @Override
    public BDDiskUInfoResDto getUInfo(int userid, boolean coverMsg) {

        BDDiskUInfoResDto bdDiskUInfoResDto = null;
        final RBucket<BDDiskUInfoResDto> bdDiskUInfoResDtoRBucket = redissonClient.getBucket(CacheUserDefine.BD_DISK_USER_MSG.formatKey(String.valueOf(userid)));

        if(!coverMsg) {
            bdDiskUInfoResDto = bdDiskUInfoResDtoRBucket.get();
            if(RObjectsUtils.isNull(bdDiskUInfoResDto)){
                bdDiskUInfoResDto = ibdDiskUInfoResServicel.select(userid);
                log.info("缓存没有用户信息，从数据库查询");
                if(RObjectsUtils.nonNull(bdDiskUInfoResDto)){
                    log.info("数据库存在数据，缓存redis");
                    bdDiskUInfoResDtoRBucket.set(bdDiskUInfoResDto);
                }
            }else {
                log.info("从缓存中取出用户信息");
            }
        }

        if(RObjectsUtils.isNull(bdDiskUInfoResDto)) {
            log.info("从接口请求用户信息");
            String access_token = ibdDiskOfferAuthService.offerAccessToken(userid);
            BDDiskUInfoReqDto bdDiskUInfoReqDto = new BDDiskUInfoReqDto(access_token);
            String uInfoUrl = bdDiskUInfoReqDto.toStringWithBaseUrl(appConfigurationBDDisk.getXpan_nas());

            HttpParamDto httpParamDto = new HttpParamDto();
            httpParamDto.setUrl(uInfoUrl);

            String resultJson = httpApacheImpl.getMsg(httpParamDto);
            bdDiskUInfoResDto = JSONObject.parseObject(resultJson, BDDiskUInfoResDto.class);
            AppCode.A01400.assertHasTrue(Objects.nonNull(bdDiskUInfoResDto));
            String errno = bdDiskUInfoResDto.getErrno();
            if (!BDDiskErrorCode.success.equals(errno)) {
                Map<String, String> map = BDDiskErrorCode.errorCodeMap;
                if (map.containsKey(errno)) {
                    throw AppCode.A01400.toUserException(map.get(errno));
                } else {
                    throw AppCode.A01400.toUserException(bdDiskUInfoResDto.getErrmsg());
                }
            }
            // 不设置时间，永久有效，如果修改则重新覆盖
            bdDiskUInfoResDtoRBucket.set(bdDiskUInfoResDto);
            ibdDiskUInfoResServicel.insert(bdDiskUInfoResDto, userid);
        }
        // {"avatar_url":"https://dss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portrait/item/netdisk.1.9242b42d.EVgwXTKacOKIKnJE2-v9kw.jpg","baidu_name":"工藤cros","createTime":"2022-05-22 11:53:56","createUserid":1,"effect":"EFFECT","errmsg":"succ","errno":"0","id":0,"netdisk_name":"","status":"EFFECT","uk":707622832,"updateTime":"2022-05-22 11:53:56","updateUserid":1,"uuid":"efa19ff5b2f345c585fe6ce76052623e","vip_type":0}
        log.info("用户信息为: {}",bdDiskUInfoResDto);

        return bdDiskUInfoResDto;
    }
}
