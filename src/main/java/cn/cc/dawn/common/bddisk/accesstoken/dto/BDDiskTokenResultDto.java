/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 16:36
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * code换access_token的返回值
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BDDiskTokenResultDto extends BDDiskResulCommonFiledtDto{

    /**
     * 请求token时的code
     * 保存请求时的code，如果后续多次刷新记录刷新历史
     */
    private String code;
    /**
     * 获取到的授权token，作为调用其他接口访问用户数据的凭证
     */
    private String access_token;
    /**
     * access_token的有效期，单位：秒
     */
    private int expires_in;
    /**
     * 用于刷新access_token, 有效期为10年
     */
    private String refresh_token;
    /**
     * access_token最终的访问权限，即用户的实际授权列表
     */
    private String scope;

}
