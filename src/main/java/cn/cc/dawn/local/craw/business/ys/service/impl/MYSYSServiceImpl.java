package cn.cc.dawn.local.craw.business.ys.service.impl;

import cn.cc.dawn.local.craw.business.data.dto.HttpParamDto;
import cn.cc.dawn.local.craw.business.ys.service.MYSYSService;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.ISelenium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MYSYSServiceImpl implements MYSYSService {
    private String url = "https://bbs.mihoyo.com/ys/article/";

    @Resource
    ISelenium iSelenium;

    @Override
    public String save(String id) {
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url + id);

        String html = iSelenium.getHTML(httpParamDto.getUrl()).getPageSource();
        log.info("html");
        log.info(html);
        return "success";
    }
}
