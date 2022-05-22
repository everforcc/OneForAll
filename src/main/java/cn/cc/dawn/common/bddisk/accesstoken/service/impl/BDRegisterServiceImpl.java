/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 15:18
 * Copyright
 */

package cn.cc.dawn.common.bddisk.accesstoken.service.impl;

import cn.cc.dawn.common.bddisk.cache.BDRegisterCache;
import cn.cc.dawn.common.bddisk.accesstoken.dto.BDRegisterCallBackDto;
import cn.cc.dawn.common.bddisk.accesstoken.service.IBDRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BDRegisterServiceImpl implements IBDRegisterService {

    @Cacheable(cacheNames = BDRegisterCache.BDRegister,key = "#bdRegisterCallBackDto.num")
    @Override
    public BDRegisterCallBackDto saveRedis_1(BDRegisterCallBackDto bdRegisterCallBackDto) {
        log.info("saveRedis_1");
        return bdRegisterCallBackDto;
    }

    @Cacheable(cacheNames = BDRegisterCache.BDRegister,key = "#bdRegisterCallBackDto.num")
    @Override
    public BDRegisterCallBackDto saveRedis_2(BDRegisterCallBackDto bdRegisterCallBackDto) {
        log.info("saveRedis_2");
        return bdRegisterCallBackDto;
    }
}
