/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 15:18
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
