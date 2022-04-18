package cn.cc.dawn.local.craw.web.data.dao;

import cn.cc.dawn.local.craw.web.data.dto.WebSiteDto;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface WebSiteMapper {

    List<WebSiteDto> selectAll();

    WebSiteDto selectByUrl(String webroot);

    String existByUrl(String webroot);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(WebSiteDto webSiteDto);

}
