/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 15:17
 */

package cn.cc.dawn.common.bddisk.accesstoken.serv;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDRegisterCallBackDto;

/**
 * 扫码返回code
 * 保存到redis,等待接下来验证
 */
public interface IBDRegisterService {

    /**
     * 接口类型1
     * 测试不同参数
     *
     * @param bdRegisterCallBackDto 返回链接参数
     * @return 返回链接参数
     */
    BDRegisterCallBackDto saveRedis_1(BDRegisterCallBackDto bdRegisterCallBackDto);

    /**
     * 接口类型2
     * 测试不同参数
     *
     * @param bdRegisterCallBackDto 返回链接参数
     * @return 返回链接参数
     */
    BDRegisterCallBackDto saveRedis_2(BDRegisterCallBackDto bdRegisterCallBackDto);

}
