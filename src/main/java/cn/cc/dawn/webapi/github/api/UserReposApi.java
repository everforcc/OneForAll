/**
 * @Description
 * @Author everforcc
 * @Date 2022-07-03 11:37
 * Copyright
 */

package cn.cc.dawn.webapi.github.api;

import cn.cc.dawn.utils.enums.HttpTypeEnum;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.dto.HttpParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 从github接口获取仓库信息
 */
@Component
public class UserReposApi {

    @Autowired
    IHttp httpApacheImpl;

    public String user_repos(){
        String url = "https://api.github.com/user/repos?visibility=private&sort=created";
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setHttpTypeEnum(HttpTypeEnum.GET);
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization", "token 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");
        httpParamDto.setHeaders(headers);
        httpParamDto.setUrl(url);

        String json = httpApacheImpl.getMsg(httpParamDto);

        return "";
    }

}
