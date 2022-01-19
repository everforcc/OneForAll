package cn.cc.dawn.common.dao;

import cn.cc.dawn.common.dto.WebSiteDo;

public interface WebSiteDoMapper {

    WebSiteDo selectBYUrlParams(String url,String params);

    int insert(WebSiteDo webSiteDo);

}
