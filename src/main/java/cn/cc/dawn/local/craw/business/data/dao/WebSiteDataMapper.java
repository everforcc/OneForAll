package cn.cc.dawn.local.craw.business.data.dao;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(WebSiteDataDto condation);

    String existBYParentid(WebSiteDataDto condation);

    int insert(WebSiteDataDto webSiteDo);

}
