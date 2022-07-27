/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-17 15:19
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 扫二维码跳转链接
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BDRegisterCallBackDto {

    private static int i = 1;

    private String expires_in;
    private String access_token;
    private String session_secret;
    private String session_key;
    private String scope;
    private String code;
    private int num = i++;

}
