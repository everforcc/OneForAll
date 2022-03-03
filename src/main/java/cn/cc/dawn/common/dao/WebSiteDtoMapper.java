package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.WebSiteDto;

import java.util.List;

public interface WebSiteDtoMapper {

    List<WebSiteDto> selectAll();

    WebSiteDto selectByUrl(String webroot);

    int insert(WebSiteDto webSiteDto);

}
