package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(int parentid);

    String existBYParentid(int parentid);

    int insert(WebSiteDataDto webSiteDo);

}
