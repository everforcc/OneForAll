package cn.cc.dawn.local.craw.business.ys.service.impl;

import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.local.craw.business.ys.service.MYSYSService;
import cn.cc.dawn.utils.http.IHttp;
import cn.cc.dawn.utils.http.impl.IHttpSeleniumImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MYSYSServiceImpl implements MYSYSService {
    private String url = "https://bbs.mihoyo.com/ys/article/";

    IHttp IHttp = new IHttpSeleniumImpl();

    @Override
    public String save(String id) {
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url + id);

        String html = IHttp.getMsg(httpParamDto);
        log.info("html");
        log.info(html);
        return "success";
    }
}
