package cn.cc.dawn.local.craw.web.data.service;

import cn.cc.dawn.config.init.yml.APPConfiguration;
import cn.cc.dawn.local.craw.web.data.dao.WebSiteDataMapper;
import cn.cc.dawn.local.craw.web.data.dto.WebSiteDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Validated
@Transactional
public class WebSiteDataService {

    /**
     * save web数据数据表
     */

    @Autowired
    APPConfiguration appConfiguration;
    @Resource
    WebSiteDataMapper webSiteDataMapper;

    @Transactional(rollbackFor = Exception.class)
    public WebSiteDataDto insert(@NotNull(message = "[obj] 不能为null") WebSiteDataDto webSiteDataDto){

        /**
         * 持久化到磁盘
         */
        String filePath = appConfiguration.getFilepath();

        WebSiteDataDto condation = new WebSiteDataDto();
        condation.setParentid(webSiteDataDto.getParentid());

        if("1".equals(webSiteDataMapper.existBYParentid(condation))){

            /*WebSiteDataDto condation = new WebSiteDataDto();
            condation.setParentid(webSiteDataDto.getParentid());*/
//            condation.setStatus(StatusEnum.EFFECT);
//            condation.setEffect(StatusEnum.EFFECT);

//            webSiteDataDto = webSiteDataMapper.selectBYParentid(condation);
//            log.info("数据表已存在: " + webSiteDataDto.getParentid());

            //if(StatusEnum.UNEFFECT == webSiteDataDto.getType()){
            if(0 == webSiteDataDto.getType()){
                // 将数据放入磁盘
            }else {

            }

        }else {
            int dataResult = webSiteDataMapper.insert(webSiteDataDto);
            log.info("dataResult: " + dataResult);
        }
        return webSiteDataDto;
    }


    public WebSiteDataDto select(@NotNull int parentid){
        WebSiteDataDto condation = new WebSiteDataDto();
        condation.setParentid(parentid);
        return webSiteDataMapper.selectBYParentid(condation);
    }

}
