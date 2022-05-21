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

package cn.cc.dawn.common.bddisk.service.impl;

import cn.cc.dawn.common.bddisk.cache.BDRegisterCache;
import cn.cc.dawn.common.bddisk.dto.BDRegisterDto;
import cn.cc.dawn.common.bddisk.service.IBDRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BDRegisterServiceImpl implements IBDRegisterService {

    @Cacheable(cacheNames = BDRegisterCache.BDRegister,key = "#bdRegisterDto.num")
    @Override
    public BDRegisterDto saveRedis_1(BDRegisterDto bdRegisterDto) {
        log.info("saveRedis_1");
        return bdRegisterDto;
    }

    @Cacheable(cacheNames = BDRegisterCache.BDRegister,key = "#bdRegisterDto.num")
    @Override
    public BDRegisterDto saveRedis_2(BDRegisterDto bdRegisterDto) {
        log.info("saveRedis_2");
        return bdRegisterDto;
    }
}
