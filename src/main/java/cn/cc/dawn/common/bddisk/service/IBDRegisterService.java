/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 15:17
 * Copyright
 */

package cn.cc.dawn.common.bddisk.service;

import cn.cc.dawn.common.bddisk.dto.BDRegisterDto;

/**
 *
 */
public interface IBDRegisterService {

    BDRegisterDto saveRedis_1(BDRegisterDto bdRegisterDto);

    BDRegisterDto saveRedis_2(BDRegisterDto bdRegisterDto);

}
