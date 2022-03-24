package cn.cc.dawn.demo.craw.webcommon.dao;

import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(int parentid);

    String existBYParentid(int parentid);

    int insert(WebSiteDataDto webSiteDo);

}
