package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.WebSiteTagDto;

public interface WebSiteTagMapper {

    String existByUrl(String weburl);

    WebSiteTagDto selectByUrl(String weburl);

    int insert(WebSiteTagDto webSiteTagDto);

}
