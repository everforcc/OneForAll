package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.WebSiteTagDto;

public interface WebSiteTagMapper {

    String existByUrl(WebSiteTagDto webSiteTagDto);

    WebSiteTagDto selectByUrl(WebSiteTagDto webSiteTagDto);

    int insert(WebSiteTagDto webSiteTagDto);

}
