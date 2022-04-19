package cn.cc.dawn.local.craw.ys.service.impl;

import cn.cc.dawn.local.craw.web.data.dto.HttpParamDto;
import cn.cc.dawn.local.craw.ys.service.MYSYSService;
import cn.cc.dawn.utils.http.HttpMethod;
import cn.cc.dawn.utils.http.impl.HttpApacheImpl;
import cn.cc.dawn.utils.http.impl.HttpSeleniumImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MYSYSServiceImpl implements MYSYSService {
    private String url = "https://bbs.mihoyo.com/ys/article/";

    HttpMethod httpMethod = new HttpSeleniumImpl();

    @Override
    public String save(String id) {
        HttpParamDto httpParamDto = new HttpParamDto();
        httpParamDto.setUrl(url + id);

        String html = httpMethod.getMsg(httpParamDto);
        log.info("html");
        log.info(html);
        return "success";
    }
}
