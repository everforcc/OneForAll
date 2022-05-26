package cn.cc.dawn.local.craw.business.data.service;

import cn.cc.dawn.local.craw.business.data.dto.WebSiteTagDto;
import cn.cc.dawn.utils.http.vo.WebSiteReqVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface IWebSiteTagService {

    int insert(@NotEmpty(message = "入参不允许为空") String json);

    WebSiteTagDto insert(@NotNull(message = "[obj] 不能为null") WebSiteReqVO webSiteReqVO);

}
