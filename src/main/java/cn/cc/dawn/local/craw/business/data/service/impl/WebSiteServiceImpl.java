package cn.cc.dawn.local.craw.business.data.service.impl;

import cn.cc.dawn.local.craw.business.data.cache.WebDataCache;
import cn.cc.dawn.local.craw.business.data.dao.WebSiteMapper;
import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import cn.cc.dawn.local.craw.business.data.service.IWebSiteService;
import cn.cc.dawn.utils.http.vo.WebSiteDataVO;
import cn.cc.dawn.local.craw.util.CrawDataParse;
import cn.cc.dawn.utils.exception.AppCode;
import cn.cc.dawn.utils.http.ISelenium;
import cn.cc.dawn.utils.i.valited.ISave;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@Service
//@Validated
@Transactional
//@RequiredArgsConstructor
public class WebSiteServiceImpl implements IWebSiteService {

    @Resource
    private WebSiteMapper webSiteMapper;
    @Resource
    ISelenium iSelenium;
    /**
     * 主表
     *
     * 1. 插入首页类型
     * 插入前先查询是否存在
     *
     * 2. 插入子表
     * 网页名，类型
     */

    public List<WebSiteDto> selectAll(){
        return webSiteMapper.selectAll();
    }

    /**
     * TODO 防止查询多可以用redis缓存来代替
     * @param webSiteDto
     * @return
     */
    @Validated({ISave.class})
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(cacheNames = WebDataCache.WebDataCacheMain,key = "#webSiteDto.webroot")
    //public WebSiteDto insert(@Valid WebSiteDto webSiteDto){
    public WebSiteDto insert(WebSiteDto webSiteDto){

        WebSiteDto insertDto = new WebSiteDto();
        /**
         * 处理为网站根目录
         */
        String rootUrl = CrawDataParse.urlRoot(webSiteDto.getWebroot());

        if("1".equals(webSiteMapper.existByUrl(rootUrl))){
            insertDto = webSiteMapper.selectByUrl(rootUrl);
            log.info("web主表已存在: " + webSiteDto.getWebroot());
        }else {
            /**
             * 没有的话再查询出具并保存
             */
            WebSiteDataVO webSiteDataVO = iSelenium.getHTML(rootUrl);
            insertDto.setWebroot(rootUrl);
            insertDto.setWebname(webSiteDataVO.getTitle());
            insertDto.setSofttype(webSiteDto.getSofttype());
            insertDto.setWebtype(webSiteDto.getWebtype());

            webSiteMapper.insert(insertDto);
            //webSiteDto = insertDto;
            log.info("web主表已插入 ");
        }
        return insertDto;
    }

    /**
     * 插入网站主表数据, 根目录，类型，有效性
     *
     * 用json是因为使用了公共的dao层，在service限制字段的录入
     * @param json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(@NotEmpty(message = "入参不允许为空") String json){

        JSONObject webSiteJSON = JSONObject.parseObject(json);
        WebSiteDto webSiteDto = new WebSiteDto();

        /**
         * 必填字段校验
         */
        if(webSiteJSON.containsKey("webroot")){
            webSiteDto.setWebroot(webSiteJSON.getString("webroot"));
        }else {
            throw AppCode.A01000.toUserException();
        }
        if(webSiteJSON.containsKey("webname")){
            webSiteDto.setWebname(webSiteJSON.getString("webname"));
        }else {
            throw AppCode.A01001.toUserException();
        }
        if(webSiteJSON.containsKey("softtype")){
            webSiteDto.setSofttype(webSiteJSON.getString("softtype"));
        }else {
            throw AppCode.A01002.toUserException();
        }
        if(webSiteJSON.containsKey("webtype")){
            webSiteDto.setWebtype(webSiteJSON.getString("webtype"));
        }else {
            throw AppCode.A01003.toUserException();
        }
        if(webSiteJSON.containsKey("vip")){
            webSiteDto.setVip(webSiteJSON.getString("vip"));
        }else {
            throw AppCode.A01004.toUserException();
        }


        /**
         * 可选字段
         */
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

        AppCode.A00201.assertHasUpdate(webSiteMapper.insert(webSiteDto));

    }



}
