/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 15:17
 */

package cn.cc.dawn.common.bddisk.accesstoken.service;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDRegisterCallBackDto;

/**
 *
 */
public interface IBDRegisterService {

    BDRegisterCallBackDto saveRedis_1(BDRegisterCallBackDto bdRegisterCallBackDto);

    BDRegisterCallBackDto saveRedis_2(BDRegisterCallBackDto bdRegisterCallBackDto);

}
