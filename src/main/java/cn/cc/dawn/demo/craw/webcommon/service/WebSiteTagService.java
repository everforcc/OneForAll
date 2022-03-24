package cn.cc.dawn.demo.craw.webcommon.service;

import cn.cc.dawn.demo.craw.webcommon.dao.WebSiteMapper;
import cn.cc.dawn.demo.craw.webcommon.dao.WebSiteTagMapper;
import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDto;
import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteTagDto;
import cn.cc.dawn.utils.http.HttpParamUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class WebSiteTagService {

    @Resource
    private WebSiteTagMapper webSiteTagDtoMapper;
    @Resource
    private WebSiteMapper webSiteDtoMapper;

    public int insert(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();

        if(jsonObject.containsKey("weburl")){
            /**
             * 1. 先查询子表是否存在
             */

            String weburl = jsonObject.getString("weburl");
            String rootUrl = HttpParamUtils.getRootUrl(weburl);
            WebSiteDto webSiteDto = webSiteDtoMapper.selectByUrl(rootUrl);

            // 如果root不存在，插入到主表
            if (Objects.isNull(webSiteDto)){
                webSiteDto = new WebSiteDto();
                webSiteDto.setWebroot(rootUrl);
                webSiteDto.setWebname(jsonObject.getString("webrootname"));
                webSiteDto.setWebtype(jsonObject.getString("webtype"));
                webSiteDto.setSofttype(jsonObject.getString("softtype"));
                webSiteDtoMapper.insert(webSiteDto);
                // 组织数据插入子表
                /**
                 * TODO 插入后查询后返回id
                 */
                webSiteDto = webSiteDtoMapper.selectByUrl(rootUrl);
            }

            webSiteTagDto.setWeburl(weburl);
            webSiteTagDto.setWebname(jsonObject.getString("webname"));
            webSiteTagDto.setWebrootid(webSiteDto.getId());
            return webSiteTagDtoMapper.insert(webSiteTagDto);
        }
        return 0;
    }

    public WebSiteTagDto insert(WebSiteTagDto webSiteTagDto){
        if("1".equals(webSiteTagDtoMapper.existByUrl(webSiteTagDto.getWeburl()))){
            webSiteTagDto = webSiteTagDtoMapper.selectByUrl(webSiteTagDto.getWeburl());
            log.info("tag表已存在: " + webSiteTagDto.getWeburl());
        }else {
            int tagResult = webSiteTagDtoMapper.insert(webSiteTagDto);
            log.info("tagResult: " + tagResult);
        }
        return webSiteTagDto;
    }

}
