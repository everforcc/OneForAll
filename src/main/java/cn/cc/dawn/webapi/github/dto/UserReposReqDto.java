/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-02 18:25
 * Copyright
 */

package cn.cc.dawn.webapi.github.dto;

import cn.cc.dawn.utils.annotation.ReflectFileFiled;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReposReqDto {

    // https://api.github.com/user/repos?visibility=private&sort=created&direction=asc
    @ReflectFileFiled(use = false)
    private String url = "https://api.github.com/user/repos";

    /** 私有仓库 */
    private String visibility = "private";

    /** 按照创建时间排序 */
    private String sort = "created";

    /** 默认设置按照时间正序 */
    private String direction = "asc";

    /** 查找用户的token */
    @ReflectFileFiled(use = false)
    private String token;

    public String toStringWithBaseUrl() {
        return url + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat);
    }
}
