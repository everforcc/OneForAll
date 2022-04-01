package cn.cc.dawn.open.web.data.dao;

import cn.cc.dawn.open.web.data.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(WebSiteDataDto condation);

    String existBYParentid(WebSiteDataDto condation);

    int insert(WebSiteDataDto webSiteDo);

}
