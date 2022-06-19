/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-22 10:08
 */

package cn.cc.dawn.common.bddisk.uinfo.dto;

import cn.cc.dawn.utils.dto.CommonFiledDto;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * bddisk用户信息
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BDDiskUInfoResDto extends CommonFiledDto {

    /**
     * 百度账号
     */
    private String baidu_name;
    /**
     * 网盘账号
     */
    private String netdisk_name;
    /**
     * 头像地址
     */
    private String avatar_url;
    /**
     * 会员类型，0普通用户、1普通会员、2超级会员
     */
    private int vip_type;
    /**
     * 用户ID
     */
    private Long uk;
    /**
     * 错误码
     * 如果为0则表示正确
     */
    private String errno;
    /**
     * 错误信息
     */
    private String errmsg;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
