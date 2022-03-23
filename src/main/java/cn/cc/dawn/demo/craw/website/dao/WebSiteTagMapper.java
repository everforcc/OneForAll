package cn.cc.dawn.demo.craw.website.dao;

import cn.cc.dawn.demo.craw.website.dto.WebSiteTagDto;

public interface WebSiteTagMapper {

    String existByUrl(String weburl);

    WebSiteTagDto selectByUrl(String weburl);

    int insert(WebSiteTagDto webSiteTagDto);

}
