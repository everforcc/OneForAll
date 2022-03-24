package cn.cc.dawn.demo.craw.webcommon.dao;

import cn.cc.dawn.demo.craw.webcommon.dto.WebSiteDto;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface WebSiteMapper {

    List<WebSiteDto> selectAll();

    WebSiteDto selectByUrl(String webroot);

    String existByUrl(String webroot);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(WebSiteDto webSiteDto);

}
