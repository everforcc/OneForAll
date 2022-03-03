package cn.cc.dawn.common.service;

import cn.cc.dawn.common.dao.WebSiteDtoMapper;
import cn.cc.dawn.common.dto.WebSiteDo;
import cn.cc.dawn.common.dto.WebSiteDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class WebSiteService {

    @Resource
    private WebSiteDtoMapper accountsDaoMapper;

    /**
     * 1. 插入首页类型
     * 插入前先查询是否存在
     *
     * 2. 插入子表
     * 网页名，类型
     */

    public List<WebSiteDto> selectAll(){
        return accountsDaoMapper.selectAll();
    }

    public int insert(String json){
        JSONObject webSiteJSON = JSONObject.parseObject(json);

        WebSiteDto webSiteDto = new WebSiteDto();

        if(webSiteJSON.containsKey("webroot")){
            webSiteDto.setWebroot(webSiteJSON.getString("webroot"));
        }

        if(webSiteJSON.containsKey("webname")){
            webSiteDto.setWebname(webSiteJSON.getString("webname"));
        }

        if(webSiteJSON.containsKey("softtype")){
            webSiteDto.setSofttype(webSiteJSON.getString("softtype"));
        }

        if(webSiteJSON.containsKey("webtype")){
            webSiteDto.setWebtype(webSiteJSON.getString("webtype"));
        }

        if(webSiteJSON.containsKey("username")){
            webSiteDto.setUsername(webSiteJSON.getString("username"));
        }

        if(webSiteJSON.containsKey("password")){
            webSiteDto.setPassword(webSiteJSON.getString("password"));
        }

        if(webSiteJSON.containsKey("phone")){
            webSiteDto.setPhone(webSiteJSON.getString("phone"));
        }

        if(webSiteJSON.containsKey("email")){
            webSiteDto.setEmail(webSiteJSON.getString("email"));
        }

        if(webSiteJSON.containsKey("cookie")){
            webSiteDto.setCookie(webSiteJSON.getString("cookie"));
        }

        if(webSiteJSON.containsKey("safequestion")){
            webSiteDto.setSafequestion(webSiteJSON.getString("safequestion"));
        }

        if(webSiteJSON.containsKey("discription")){
            webSiteDto.setDiscription(webSiteJSON.getString("discription"));
        }

        if(webSiteJSON.containsKey("vip")){
            webSiteDto.setVip(webSiteJSON.getIntValue("vip"));
        }

        int result = accountsDaoMapper.insert(webSiteDto);
        System.out.println(result);
        return result;
    }



}
