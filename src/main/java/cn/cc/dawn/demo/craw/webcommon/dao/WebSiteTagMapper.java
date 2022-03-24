package cn.cc.dawn.demo.craw.webcommon.dao;

import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteTagDto;

public interface WebSiteTagMapper {

    String existByUrl(String weburl);

    WebSiteTagDto selectByUrl(String weburl);

    int insert(WebSiteTagDto webSiteTagDto);

}
