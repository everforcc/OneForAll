/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-17 15:19
 * Copyright
 */

package cn.cc.dawn.local.craw.business.bdwp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BDRegisterDto {

    private static int i = 1;

    private String expires_in;
    private String access_token;
    private String session_secret;
    private String session_key;
    private String scope;
    private String code;
    private int num = i++;

}
