package cn.cc.dawn.local.craw.web.data.dao;

import cn.cc.dawn.local.craw.web.data.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(WebSiteDataDto condation);

    String existBYParentid(WebSiteDataDto condation);

    int insert(WebSiteDataDto webSiteDo);

}
