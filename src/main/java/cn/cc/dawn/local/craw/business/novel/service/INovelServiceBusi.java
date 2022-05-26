package cn.cc.dawn.local.craw.business.novel.service;

import cn.cc.dawn.utils.http.vo.WebSiteReqVO;

public interface INovelServiceBusi {

    /**
     * 从url拿出数据
     */
    void novelContent(WebSiteReqVO webSiteReqVO);

}
