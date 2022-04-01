package cn.cc.dawn.open.web.data.dao;

import cn.cc.dawn.open.web.data.dto.WebSiteTagDto;

public interface WebSiteTagMapper {

    String existByUrl(String weburl);

    WebSiteTagDto selectByUrl(String weburl);

    int insert(WebSiteTagDto webSiteTagDto);

}
