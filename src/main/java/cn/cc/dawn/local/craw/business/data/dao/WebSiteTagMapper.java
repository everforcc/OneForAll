package cn.cc.dawn.local.craw.business.data.dao;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
import org.apache.ibatis.annotations.Options;

public interface WebSiteTagMapper {

    String existByUrl(String weburl);

    WebSiteTagDto selectByUrl(String weburl);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(WebSiteTagDto webSiteTagDto);

}
