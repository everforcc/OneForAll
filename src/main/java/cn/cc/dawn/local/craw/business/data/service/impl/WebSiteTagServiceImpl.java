package cn.cc.dawn.local.craw.business.data.service.impl;

import cn.cc.dawn.local.craw.business.data.dao.WebSiteMapper;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteTagMapper;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteService;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteTagService;
import cn.cc.dawn.local.craw.business.data.vo.WebSiteDataVO;
import cn.cc.dawn.local.craw.business.data.vo.WebSiteReqVO;
import cn.cc.dawn.utils.check.StringUtils;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.HttpParamUtils;
import cn.cc.dawn.utils.http.ISelenium;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
//@Validated
@Transactional
public class WebSiteTagServiceImpl implements IWebSiteTagService {

    /**
     * tag表
     */
    @Resource
    ISelenium iSelenium;
    @Resource
    private WebSiteTagMapper webSiteTagDtoMapper;
    @Resource
    private WebSiteMapper webSiteDtoMapper;
    @Resource
    IWebSiteService iWebSiteService;

    @Transactional(rollbackFor = Exception.class)
    public int insert(@NotEmpty(message = "入参不允许为空") String json){
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

    // @NotNull(message = "[obj] 不能为null")
    public WebSiteTagDto insert(WebSiteReqVO webSiteReqVO){

        WebSiteTagDto webSiteTagDto = new WebSiteTagDto();
        String weburl = webSiteReqVO.getWeburl();


        if("1".equals(webSiteTagDtoMapper.existByUrl(weburl))){
            webSiteTagDto = webSiteTagDtoMapper.selectByUrl(weburl);
            log.info("tag表已存在: " + webSiteTagDto.getWeburl());
        }else {
            /**
             * 1. 存之前先存入主表
             * 2. 校验必填字段
             */
            WebSiteDto webSiteDto = new WebSiteDto(weburl);

            AppCode.A00163.assertHasTrue(StringUtils.isNotEmpty(webSiteReqVO.getSofytype()));
            AppCode.A00163.assertHasTrue(StringUtils.isNotEmpty(webSiteReqVO.getWebtype()));
            webSiteDto.setSofttype(webSiteReqVO.getSofytype());
            webSiteDto.setWebtype(webSiteReqVO.getWebtype());
            // 插入
            webSiteDto = iWebSiteService.insert(webSiteDto);

            WebSiteDataVO webSiteDataVO = iSelenium.getHTML(weburl);
            // 请求数据获取title
            webSiteTagDto.setWebname(webSiteDataVO.getTitle());
            webSiteTagDto.setWebrootid(webSiteDto.getId());
            webSiteTagDto.setWeburl(weburl);
            // 插入tag表，
            int tagResult = webSiteTagDtoMapper.insert(webSiteTagDto);
            log.info("tagResult: " + tagResult);
        }
        return webSiteTagDto;
    }

}
