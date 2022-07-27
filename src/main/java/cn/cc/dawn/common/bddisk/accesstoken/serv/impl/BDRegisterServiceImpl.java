/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 15:18
 */

package cn.cc.dawn.common.bddisk.accesstoken.serv.impl;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDRegisterCallBackDto;
import cn.cc.dawn.common.bddisk.accesstoken.serv.IBDRegisterService;
import cn.cc.dawn.common.bddisk.cache.BDRegisterCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 扫码返回code
 * 保存到redis,等待接下来验证
 */
@Slf4j
@Service
public class BDRegisterServiceImpl implements IBDRegisterService {

    /**
     * 测试回调接口1
     *
     * @param bdRegisterCallBackDto 返回链接参数
     * @return 返回链接参数
     */
    @Cacheable(cacheNames = BDRegisterCache.BDRegister, key = "#bdRegisterCallBackDto.num")
    @Override
    public BDRegisterCallBackDto saveRedis_1(BDRegisterCallBackDto bdRegisterCallBackDto) {
        log.info("saveRedis_1");
        return bdRegisterCallBackDto;
    }

    /**
     * 测试回调接口2
     *
     * @param bdRegisterCallBackDto 返回链接参数
     * @return 返回链接参数
     */
    @Cacheable(cacheNames = BDRegisterCache.BDRegister, key = "#bdRegisterCallBackDto.num")
    @Override
    public BDRegisterCallBackDto saveRedis_2(BDRegisterCallBackDto bdRegisterCallBackDto) {
        log.info("saveRedis_2");
        return bdRegisterCallBackDto;
    }
}
