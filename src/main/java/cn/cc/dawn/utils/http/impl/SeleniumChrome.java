package cn.cc.dawn.utils.http.impl;

import cn.cc.dawn.utils.http.vo.WebSiteDataVO;
import cn.cc.dawn.utils.http.ISelenium;
import cn.cc.dawn.utils.http.selenium.SeleniumPool;
import cn.cc.dawn.utils.http.selenium.WebDriverPDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SeleniumChrome implements ISelenium {

    @Override
    public WebSiteDataVO getHTML(String url,SeleniumPool seleniumPool) {
        WebDriverPDto webDriverPDto = seleniumPool.getDriverDto();
        WebSiteDataVO webSiteDataVO = webDriverPDto.getHtml(url);
        seleniumPool.close(webDriverPDto);
        return webSiteDataVO;
    }

    @Override
    public WebSiteDataVO getHTML(String url) {
        SeleniumPool seleniumPool  = SeleniumPool.getInstantce();
        WebDriverPDto webDriverPDto = seleniumPool.getDriverDto();
        WebSiteDataVO webSiteDataVO = webDriverPDto.getHtml(url);
        seleniumPool.close(webDriverPDto);
        return webSiteDataVO;
    }

}
