package cn.cc.dawn.local.craw.business.data.service;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteDataDto;
import cn.cc.dawn.local.craw.business.data.vo.WebSiteReqVO;

import javax.validation.constraints.NotNull;

public interface IWebSiteDataService {

    /**
     * 通用的，根据网址请求并保存数据
     * @param webSiteReqVO
     * @return
     */
    WebSiteDataDto insert(@NotNull(message = "[obj] 不能为null") WebSiteReqVO webSiteReqVO);

    WebSiteDataDto select(@NotNull int parentid);
}
