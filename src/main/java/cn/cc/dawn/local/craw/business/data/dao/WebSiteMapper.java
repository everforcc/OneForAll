package cn.cc.dawn.local.craw.business.data.dao;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteDto;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface WebSiteMapper {

    List<WebSiteDto> selectAll();

    WebSiteDto selectByUrl(String webroot);

    String existByUrl(String webroot);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(WebSiteDto webSiteDto);

}
