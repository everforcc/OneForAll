package cn.cc.dawn.demo.craw.website.service;

import cn.cc.dawn.demo.craw.website.dao.WebSiteDataMapper;
import cn.cc.dawn.demo.craw.website.dto.WebSiteDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
@Transactional
public class WebSiteDataService {

    @Resource
    WebSiteDataMapper webSiteDataMapper;

    public WebSiteDataDto insert(WebSiteDataDto webSiteDataDto){
        if("1".equals(webSiteDataMapper.existBYParentid(webSiteDataDto.getParentid()))){
            webSiteDataDto = webSiteDataMapper.selectBYParentid(webSiteDataDto.getParentid());
            log.info("数据表已存在: " + webSiteDataDto.getParentid());
        }else {
            int dataResult = webSiteDataMapper.insert(webSiteDataDto);
            log.info("dataResult: " + dataResult);
        }
        return webSiteDataDto;
    }

}
