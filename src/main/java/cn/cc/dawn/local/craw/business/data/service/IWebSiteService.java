package cn.cc.dawn.local.craw.business.data.service;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import java.util.List;

public interface IWebSiteService {

    List<WebSiteDto> selectAll();

    WebSiteDto insert(WebSiteDto webSiteDto);

    void insert(String json);

}
