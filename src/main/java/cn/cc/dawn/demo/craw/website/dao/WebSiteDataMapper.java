package cn.cc.dawn.demo.craw.website.dao;

import cn.cc.dawn.demo.craw.website.dto.WebSiteDataDto;

public interface WebSiteDataMapper {

    WebSiteDataDto selectBYParentid(int parentid);

    String existBYParentid(int parentid);

    int insert(WebSiteDataDto webSiteDo);

}
