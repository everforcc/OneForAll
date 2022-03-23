package cn.cc.dawn.demo.craw.website.service;

import cn.cc.dawn.demo.craw.website.dao.WebSiteMapper;
import cn.cc.dawn.demo.craw.website.dto.WebSiteDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional
//@RequiredArgsConstructor
public class WebSiteService {

    @Resource
    private WebSiteMapper webSiteMapper;

    /**
     * 1. 插入首页类型
     * 插入前先查询是否存在
     *
     * 2. 插入子表
     * 网页名，类型
     */

    public List<WebSiteDto> selectAll(){
        return webSiteMapper.selectAll();
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

        int result = webSiteMapper.insert(webSiteDto);
        log.info("result: " + result);
        return result;
    }

    public WebSiteDto insert(WebSiteDto webSiteDto){
        if("1".equals(webSiteMapper.existByUrl(webSiteDto.getWebroot()))){
            webSiteDto = webSiteMapper.selectByUrl(webSiteDto.getWebroot());
            log.info("web主表已存在: " + webSiteDto.getWebroot());
        }else {
            int webResult = webSiteMapper.insert(webSiteDto);
            log.info("webResult: " + webResult);
        }
        return webSiteDto;
    }



}
